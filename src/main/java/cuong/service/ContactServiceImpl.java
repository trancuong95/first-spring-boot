package cuong.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cuong.domain.Contact;
import cuong.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

	 @Autowired
	    private ContactRepository contactRepository;

	    @Override
	    public Iterable<Contact> findAll() {
	        return contactRepository.findAll();
	    }

	    @Override
	    public List<Contact> search(String term) {
	        return contactRepository.findByNameContaining(term);
	    }

	    @Override
	    public void save(Contact contact) {
	        contactRepository.save(contact);
	    }

		@Override
		public Optional<Contact> findById(Integer id) {
			return contactRepository.findById(id);
		}

		@Override
		public void deleteById(Integer id) {
			contactRepository.deleteById(id);
			
		}
}

