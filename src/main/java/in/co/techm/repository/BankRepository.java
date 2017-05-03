package in.co.techm.repository;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import in.co.techm.model.Bank;

@RepositoryRestResource
public interface BankRepository extends MongoRepository<Bank, Serializable> {
	Bank findByBranchAndBank(String branch, String bank);
}



