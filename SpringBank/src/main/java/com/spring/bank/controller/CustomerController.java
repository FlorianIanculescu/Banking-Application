package com.spring.bank.controller;

import com.spring.bank.entity.Customer;
import com.spring.bank.entity.Transaction;
import com.spring.bank.security.Authority;
import com.spring.bank.security.User;
import com.spring.bank.service.AuthorityService;
import com.spring.bank.service.CustomerService;
import com.spring.bank.service.TransactionService;
import com.spring.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
public class CustomerController {

    private CustomerService customerService;
    private TransactionService transactionService;
    private UserService userService;
    private AuthorityService authorityService;

    @Autowired
    public CustomerController(CustomerService theCustomerService,
                              TransactionService theTransactionService,
                              UserService theUserService,
                              AuthorityService theAuthorityService) {
        this.customerService = theCustomerService;
        this.transactionService = theTransactionService;
        this.userService = theUserService;
        this.authorityService = theAuthorityService;
    }

    @GetMapping("/")
    public String showLandingPage() {

        return "landing";
    }

    @GetMapping("/showHome")
    public String showHome() {

        return "landing";
    }

    @GetMapping("/deposit")
    public String showDeposit() {

        return "deposit";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        Customer theCustomer = new Customer();

        theModel.addAttribute("customer", theCustomer);

        return "signup";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {

        // save the customer
        customerService.save(theCustomer);

        User theUser = new User();
        theUser.setUsername(theCustomer.getUsername());
        theUser.setPassword("{noop}" + theCustomer.getPassword());
        theUser.setEnabled(1);

        Authority theAuthority = new Authority();
        theAuthority.setUser(theUser);
        theAuthority.setAuthority("ROLE_EMPLOYEE");

        userService.save(theUser);
        authorityService.save(theAuthority);
        // use a redirect to prevent duplicate submissions
        return "login";
    }

    @GetMapping("/showFormForDeposit")
    public String showFormForDeposit(Model theModel) {

        Customer theCustomer = new Customer();

        theModel.addAttribute("customer", theCustomer);

        return "deposit";
    }

    @PostMapping("/deposit")
    public String deposit(@ModelAttribute("customer") Customer formCustomer, Principal principal) {

        String username = principal.getName();

        Customer theCustomer = customerService.findCustomerByUsername(username);

        customerService.depositMoney(formCustomer, theCustomer);

        transactionService.addNewTransaction(theCustomer, formCustomer.getBalance());

        return "redirect:/showDashboard";
    }

    @PostMapping("/withdraw")
    public String withdraw(@ModelAttribute("customer") Customer formCustomer, Principal principal) {

        String username = principal.getName();

        Customer theCustomer = customerService.findCustomerByUsername(username);

        customerService.withdrawMoney(formCustomer, theCustomer);

        transactionService.addNewTransaction(theCustomer, formCustomer.getBalance());

        return "redirect:/showDashboard";
    }

    @GetMapping("/showDashboard")
    public String showDashboard(Model theModel, Principal principal) {

        String username = principal.getName();

        Customer customer = customerService.findCustomerByUsername(username);

        List<Transaction> transactionsList = transactionService.findTransactionByCustomerId(customer.getId());

        theModel.addAttribute("transactions", transactionsList);
        theModel.addAttribute("customer", customer);

        return "dashboard";
    }

    @GetMapping("/showFormForWithdraw")
    public String showFormForSendMoney(Model theModel) {

        Customer theCustomer = new Customer();

        theModel.addAttribute("customer", theCustomer);

        return "withdraw";
    }

    @GetMapping("/showAccountInformation")
    public String showTest(Model model, Principal principal) {


        String username = principal.getName();

        Customer customer = customerService.findCustomerByUsername(username);

        model.addAttribute("customer", customer);

        return "account-information";
    }

    @GetMapping("/showTransactions")
    public String showTransactions(Model theModel, Principal principal) {

        String username = principal.getName();
        System.out.println(username);

        Customer customer = customerService.findCustomerByUsername("florian");
        System.out.println(customer);

        List<Transaction> transactionList = transactionService.findTransactionByCustomerId(customer.getId());
        System.out.println(transactionList);

        theModel.addAttribute("transactions", transactionList);

        return "transactions";
    }
}
