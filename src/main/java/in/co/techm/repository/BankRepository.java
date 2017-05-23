package in.co.techm.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import in.co.techm.model.Bank;

import javax.swing.text.html.Option;

@RepositoryRestResource
public interface BankRepository extends MongoRepository<Bank, Serializable> {

	Bank findByBranchIgnoreCaseAndBankIgnoreCase(String branch, String bank);

	Optional<List<Bank>> findByBankIgnoreCase(String bank);

	Optional<Bank> findByIfsc(String ifsc);

	Optional<Bank> findByMicrcode(String micrcode);

	@Query("{ 'branch':{$regex:?1,$options:'i'}, 'bank':{$regex:?0, $options: 'i'}}")
	Optional<List<Bank>> findByBankIgnoreCaseAndBranch(String bank, String branch);
}



