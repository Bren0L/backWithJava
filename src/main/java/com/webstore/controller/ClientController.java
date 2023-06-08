package com.webstore.controller;

import com.webstore.dtos.ClientDto;
import com.webstore.entities.ClientEntity;
import com.webstore.services.ClientAlreadyExistsException;
import com.webstore.services.ClientNotFoundException;
import com.webstore.services.ClientService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/clients")
public class ClientController {
    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> newClient(@RequestBody @Valid ClientDto clientDto){
        if(service.existsByClientName(clientDto.getClientCpf()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Client name already exists");
        if(service.existsByClientEmail(clientDto.getClientEmail()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Client email already exists");
        if(service.existsByClientCpf(clientDto.getClientCpf()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Client CPF already exists");

        ClientEntity client = new ClientEntity();
        BeanUtils.copyProperties(clientDto, client);
        client.setCadDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(client));
    }

    @GetMapping
    public ResponseEntity<List<ClientEntity>> getAllClients(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getClient(@PathVariable(value = "id") long id){
        Optional<ClientEntity> optional = service.getClient(id);
        if(optional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Any client found with id " + id);
        return ResponseEntity.status(HttpStatus.OK).body(optional.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable(value = "id") long id){
        Optional<ClientEntity> optional = service.getClient(id);
        if(optional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Any client found with id " + id);
        service.deleteClient(optional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Client deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable(value = "id") long id, @RequestBody @Valid ClientDto clientDto){
        Optional<ClientEntity> optional = service.getClient(id);
        if(optional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Any client found with id " + id);

        ClientEntity client = new ClientEntity();
        BeanUtils.copyProperties(clientDto, client);
        client.setRegistration(optional.get().getRegistration());
        client.setCadDate(optional.get().getCadDate());

        return ResponseEntity.status(HttpStatus.OK).body(service.save(client));
    }

    @GetMapping("/clients")
    public String getClients(Model model){
        List<ClientEntity> clients = service.getAllClients();
        model.addAttribute("listClients", clients);
        return "clients";
    }

    @GetMapping("/clients/new")
    public String newClient(Model model){
        model.addAttribute("client", new ClientEntity());
        model.addAttribute("title", "Add New Client");
        return "client_form";
    }

    @GetMapping("/clients/login")
    public String login(Model model){
        model.addAttribute("client", new ClientEntity());
        return "clients";
    }

    @RequestMapping(value = "/clients/login/verify", method = RequestMethod.POST)
    public String verify(@ModelAttribute ClientEntity clientEntity, ClientEntity client, RedirectAttributes ra){
        if(service.verifyClient(client)){
            ra.addFlashAttribute("getClient", service.getClientByEmail(client.getClientEmail()));
            return "redirect:/course";
        }
        ra.addFlashAttribute("message", "Login or password incorrect");
        return "redirect:/clients/login";
    }

    @PostMapping("/clients/save")
    public String saveClient(ClientEntity client, RedirectAttributes ra) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        client.setCadDate(LocalDate.now().format(formatter));
        service.save(client);
        ra.addFlashAttribute("message", "Client edited successfully");

        return "redirect:/clients";
    }

    /*@GetMapping("/clients/edit/{id}")
    public String updateClient(@PathVariable("id") long id, Model model, RedirectAttributes ra){
        model.addAttribute("title", "Edt User With Register="+id);
        try {
            ClientEntity client = service.getClient(id);
            model.addAttribute("client", client);
            return "client_form";
        } catch (ClientNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/clients";
        }
    }*/

    /*@GetMapping("/clients/delete/{id}")
    public String deleteClient(@PathVariable("id") long id, RedirectAttributes ra){
        try {
            service.deleteClient(id);
        } catch (ClientNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/clients";
    }*/

}
