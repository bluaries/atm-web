package th.ac.ku.atm.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.atm.model.BankAccount;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BankAccountService {

    private RestTemplate restTemplate;
    private List<BankAccount> bankAccountList;

    public BankAccountService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

        public List<BankAccount> getCustomerBankAccount(int customerId) {
            String url = "http://localhost:8080/api/bankaccount/customer/" +
                    customerId;
            ResponseEntity<BankAccount[]> response =
                    restTemplate.getForEntity(url, BankAccount[].class);

            BankAccount[] accounts = response.getBody();

            return Arrays.asList(accounts);
        }

        @PostConstruct
        public void postConstruct() {
            this.bankAccountList = new ArrayList<>();
        }

        public List<BankAccount> getBankAccounts(){
            return new ArrayList<>(this.bankAccountList);

        }

        public void addBankAccount(BankAccount bankAccount){
            this.bankAccountList.add(bankAccount);
        }

}