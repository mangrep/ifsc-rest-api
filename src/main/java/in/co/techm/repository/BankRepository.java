package in.co.techm.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import in.co.techm.model.Bank;

@RepositoryRestResource
public interface BankRepository extends MongoRepository<Bank, Serializable> {
	Bank findByBranchAndBank(String branch, String bank);
	Optional<List<Bank>> findByBank(String bank);

}



