package service;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.openclassrooms.safetinet.model.Person;
import com.openclassrooms.safetinet.repository.PersonsRepository;
import com.openclassrooms.safetinet.service.PersonsService;


@SpringBootTest
public class PersonServiceTest {

	private PersonsRepository personsRepository;
	private PersonsService personService;

	@Before
	public void setUp() {
		personsRepository = mock(PersonsRepository.class);
		personService = new PersonsService(personsRepository);
	}

	@Test
	public void testGetAll() {
		List<Person> expectedPersons = new ArrayList();
		expectedPersons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));
		expectedPersons.add(new Person("Jacob","Boyd","1509 Culver St","Culver","97451","841-874-6513","drk@email.com"));

		doReturn(expectedPersons).when(personsRepository).getAll();

		List<Person> actualPersons = personService.getAll();

		assertEquals(expectedPersons, actualPersons);
	}

	@Test
	public void testGetAll_Exception() {

		when(personsRepository.getAll()).thenThrow(new RuntimeException("Database connection error"));
		List<Person> actualPersons = personService.getAll();

		//assertNotNull(actualPersons);
		assertTrue(actualPersons.isEmpty());

	}
	@Test
	public void testGetPersonByAddress() {
		List<Person> expectedPersons = new ArrayList();
		expectedPersons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));

		doReturn(expectedPersons).when(personsRepository).getPersonByAddress("1509 Culver St");

		List<Person> actualPersons = personService.getPersonByAddress("1509 Culver St");

		assertEquals(expectedPersons, actualPersons);
	}


	@Test
	public void testGetPersonByAddress_Exception() {

		when(personsRepository.getPersonByAddress("1509 Culver St")).thenThrow(new RuntimeException("Database connection error"));
		List<Person> actualPersons = personService.getPersonByAddress("1509 Culver St");

		//assertNotNull(actualPersons);
		assertTrue(actualPersons.isEmpty());

	}

	@Test
	public void testGetByName() {
		List<Person> expectedPersons = new ArrayList();
		expectedPersons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));

		doReturn(expectedPersons).when(personsRepository).getByName("John", "Boyd");

		List<Person> actualPersons = personService.getByName("John", "Boyd");

		assertEquals(expectedPersons, actualPersons);
	}
	@Test
	public void testGetByName_Exception() {

		when(personsRepository.getByName("John", "Boyd")).thenThrow(new RuntimeException("Database connection error"));
		List<Person> actualPersons = personService.getByName("John", "Boyd");

		//assertNotNull(actualPersons);
		assertTrue(actualPersons.isEmpty());
	}

	@Test
	public void testSave() {
		Person person = new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com");

		doNothing().when(personsRepository).save(person);
		Optional<Person> savedPerson = personService.save(person);

		assertTrue(savedPerson.isPresent());
		assertEquals(person, savedPerson.get());
	}
	@Test
	public void testSave_Exception() {
		Person person = new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com");

		doThrow(new RuntimeException("Database connection error")).when(personsRepository).save(person);
		Optional<Person> savedPerson = personService.save(person);
		assertNotNull(savedPerson);
		assertFalse(savedPerson.isPresent());

	}

	@Test
	public void testUpdate() {
		Person personToUpdate = new Person("John","Doe","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com") ;
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));

		when(personsRepository.getAll()).thenReturn(persons);
		when(personsRepository.update(personToUpdate)).thenReturn(Optional.of(personToUpdate));
		Optional<Person> updatedPerson = personService.update("John", "Boyd", personToUpdate);

		assertTrue(updatedPerson.isPresent());
		assertEquals(personToUpdate, updatedPerson.get());	
	}
	@Test
	public void testUpdate_Exception() {
		Person personToUpdate = new Person("John","Doe","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com") ;
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));

		when(personsRepository.getAll()).thenReturn(persons);
		Optional<Person> updatedPerson = personService.update("John", "Boyd", personToUpdate);

		assertNotNull(updatedPerson);
		assertFalse(updatedPerson.isPresent());
	}

	@Test
	public void testDelete() {
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));

		when(personsRepository.getAll()).thenReturn(persons);
		Optional<Person> deletedPerson = personService.delete("John","Boyd");

		assertFalse(deletedPerson.isPresent());
		verify(personsRepository, times(1)).delete(any(), null);
	}

	@Test
	public void testDelete_NotFound() {
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));

		when(personsRepository.getAll()).thenReturn(persons);
		Optional<Person> deletedPerson = personService.delete("John","Boyd");

		assertFalse(deletedPerson.isPresent());
		verify(personsRepository, never()).delete(any(), null);

	}
	/*
	@Test
	public void testFindEmailByCity() {
		List<Person> expectedPersons = new ArrayList();
		expectedPersons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));

		doReturn(expectedPersons).when(personsRepository).findEmailByCity("Culver");

		List<Person> actualPersons = personService.getByName("John", "Boyd");

		assertEquals(expectedPersons, actualPersons);
		
	}
	*/
}
