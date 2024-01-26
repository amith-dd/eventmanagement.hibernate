package eventmanagement.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import eventmanagement.hibernate.dto.ClientService;

public class ClientServiceDao {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("amit");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();
	
	public ClientService saveClientService(ClientService clientService) {
		et.begin();
		em.persist(clientService);
		et.commit();
		return clientService;
	}
	
	public ClientService findClientService(int ClientServiceId) {
		ClientService clientService = em.find(ClientService.class, ClientServiceId);
		if(clientService!=null) {
			return clientService;
		}
		return null;
	}
	
	public ClientService updateClientService(ClientService clientService, int id) {
		ClientService exClientService = em.find(ClientService.class, id);
		if(exClientService!=null) {
			clientService.setClientServiceId(id);
			et.begin();
			em.merge(clientService);
			et.commit();
			return clientService;
		}
		return null;
	}
	
	public ClientService deleteClientService(int id) {
		ClientService exClientService = em.find(ClientService.class, id);
		if(exClientService!=null) {
			et.begin();
			em.remove(exClientService);
			et.commit();
			return exClientService;
		}
		return null;
	}
	
	public List<ClientService> getallClientServices(){
		Query query = em.createQuery("select c from ClientService c");
		return query.getResultList();
	}
	
}
