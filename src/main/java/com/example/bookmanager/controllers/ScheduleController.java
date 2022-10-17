package com.example.bookmanager.controllers;

import com.example.bookmanager.models.Address;
import com.example.bookmanager.models.Book;
import com.example.bookmanager.models.Paragraph;
import com.example.bookmanager.models.Passport;
import com.example.bookmanager.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Controller
public class ScheduleController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/schedule")
    public String ScheduleMain(Model model) {
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "schedule-main";
    }

    @GetMapping("/schedule/add")
    public String ScheduleAdd(Model model) {
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "schedule-add";
    }

    @PostMapping("/schedule/add")
    public String ScheduleAddPost(@RequestParam String title, @RequestParam String author, @RequestParam int price,
                                  @RequestParam String passport) {
        Book book = new Book(title, author, price);
        book.setPassport(new Passport(passport));
        Paragraph paragraph1 = new Paragraph("First");
        Paragraph paragraph2 = new Paragraph("Second");

        Address address1 = new Address("Kiev");
        Address address2 = new Address("Dnepr");

        book.setParagraphs(Arrays.asList(paragraph1, paragraph2));
        book.setAddresses(Arrays.asList(address1, address2));

        bookRepository.save(book);
        return "redirect:/schedule";
    }

    @GetMapping("/schedule/{id}")
    public String ScheduleDetails(@PathVariable(value = "id") long id, Model model) {
        if(!bookRepository.existsById(id)){
            return "redirect:/schedule";
        }
        Optional<Book> res = bookRepository.findById(id);
        ArrayList<Book> book = new ArrayList<>();
        res.ifPresent(book::add);
        model.addAttribute("book", book);
        return "schedule-details";
    }

    @GetMapping("/schedule/{id}/edit")
    public String ScheduleEditBook(@PathVariable(value = "id") long id,  Model model) {
        if(!bookRepository.existsById(id)){
            return "redirect:/schedule";
        }
        Optional<Book> res = bookRepository.findById(id);
        ArrayList<Book> book = new ArrayList<>();
        res.ifPresent(book::add);
        model.addAttribute("book", book);
        return "schedule-edit";
    }

    @PostMapping("/schedule/{id}/edit")
    public String SchedulePostBook(@PathVariable(value = "id") long id, @RequestParam String title,
                                   @RequestParam String author, @RequestParam int price) {

        Book book = bookRepository.findById(id).orElseThrow();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPrice(price);
        bookRepository.save(book);
        return "redirect:/schedule";
    }

    @PostMapping("/schedule/{id}/remove")
    public String ScheduleRemovePost(@PathVariable(value = "id") long id) {
        bookRepository.deleteById(id);
        return "redirect:/schedule";
    }
}
