package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.model.AnimalBreeds;
import com.example.model.AnimalColors;
import com.example.model.AnimalGender;
import com.example.model.AnimalTypes;
import com.example.repository.AnimalBreedsDao;
import com.example.repository.AnimalGenderDao;
import com.example.repository.AnimalTypeDao;
import com.example.repository.ColorsDao;

public class MainDriver {

	
	public static ApplicationContext appContext = 
			new ClassPathXmlApplicationContext("ApplicationContext.xml");
	public static AnimalTypeDao animalTypeDao = appContext.getBean("animalTypeRepo", AnimalTypeDao.class);
	public static ColorsDao colorsDao = appContext.getBean("colorsRepo", ColorsDao.class);
	public static AnimalBreedsDao breedsDao = appContext.getBean("breedsRepo", AnimalBreedsDao.class);
	public static AnimalGenderDao genderDao = appContext.getBean("genderRepo", AnimalGenderDao.class);
	
	public static void main(String[] args) {
		insertInitialValues();
		System.out.println("All the animal types: " + animalTypeDao.selectAll());
		System.out.println("All the animal colors: " + colorsDao.selectAll());
		System.out.println("All the animal breads: " + breedsDao.selectAll());
		System.out.println("All the animal genders: " + genderDao.selectAll());
	}
	
	public static void insertInitialValues() {
		
		AnimalTypes animalType = new AnimalTypes(1,"Dog");
		animalTypeDao.insert(animalType);
		animalType = new AnimalTypes(2,"Cat");
		animalTypeDao.insert(animalType);
		animalType = new AnimalTypes(3,"Bird");
		animalTypeDao.insert(animalType);
		animalType = new AnimalTypes(4,"Rodent");
		animalTypeDao.insert(animalType);
		animalType = new AnimalTypes(5,"Fish");
		animalTypeDao.insert(animalType);
		animalType = new AnimalTypes(6,"Reptile");
		animalTypeDao.insert(animalType);
		animalType = new AnimalTypes(7,"Amphibian");
		animalTypeDao.insert(animalType);
		
		//dog: id=1
		AnimalColors color = new AnimalColors(1,"Black");//animal type, color name
		colorsDao.insert(color);
		color = new AnimalColors(1,"Brindle");
		colorsDao.insert(color);
		color = new AnimalColors(1,"Brown/Chocolate");
		colorsDao.insert(color);
		color = new AnimalColors(1,"Gray/Blue/Silver/Salt & Pepper");
		colorsDao.insert(color);
		color = new AnimalColors(1,"Merle");
		colorsDao.insert(color);
		color = new AnimalColors(1,"Red/Golden/Orange/Chestnut");
		colorsDao.insert(color);
		color = new AnimalColors(1,"Silver & Tan (Yorkie colors)");
		colorsDao.insert(color);
		color = new AnimalColors(1,"Tan/Yellow/Fawn");
		colorsDao.insert(color);
		color = new AnimalColors(1,"Tricolor (Tan/Brown & Black & White)");
		colorsDao.insert(color);
		color = new AnimalColors(1,"White");
		colorsDao.insert(color);
		
		// cat: id=2
		color = new AnimalColors(2,"Black");//animal type, color name
		colorsDao.insert(color);
		color = new AnimalColors(2,"Black & White or Tuxedo");
		colorsDao.insert(color);
		color = new AnimalColors(2,"Brown or Chocolate");
		colorsDao.insert(color);
		color = new AnimalColors(2,"Brown Tabby");
		colorsDao.insert(color);
		color = new AnimalColors(2,"Calico or Dilute Calico");
		colorsDao.insert(color);
		color = new AnimalColors(2,"Cream or Ivory");
		colorsDao.insert(color);
		color = new AnimalColors(2,"Gray, Blue or Silver Tabby");
		colorsDao.insert(color);
		color = new AnimalColors(2,"Gray or Blue");
		colorsDao.insert(color);
		color = new AnimalColors(2,"Orange or Red");
		colorsDao.insert(color);
		color = new AnimalColors(2,"Orange or Red Tabby");
		colorsDao.insert(color);
		color = new AnimalColors(2,"Spotted Tabby/Leopard Spotted");
		colorsDao.insert(color);
		color = new AnimalColors(2,"Tabby");
		colorsDao.insert(color);
		color = new AnimalColors(2,"Tan or Fawn");
		colorsDao.insert(color);
		color = new AnimalColors(2,"Tan or Fawn Tabby");
		colorsDao.insert(color);
		color = new AnimalColors(2,"Tiger Striped");
		colorsDao.insert(color);
		color = new AnimalColors(2,"Tortoiseshell");
		colorsDao.insert(color);
		color = new AnimalColors(2,"White");
		colorsDao.insert(color);
		
		// bird: id=3
		color = new AnimalColors(3,"");//animal type, color name
		colorsDao.insert(color);
		color = new AnimalColors(3,"");
		colorsDao.insert(color);
		color = new AnimalColors(3,"");
		colorsDao.insert(color);
		color = new AnimalColors(3,"");
		colorsDao.insert(color);
		color = new AnimalColors(3,"");
		colorsDao.insert(color);
		color = new AnimalColors(3,"");
		colorsDao.insert(color);
		color = new AnimalColors(3,"");
		colorsDao.insert(color);
		color = new AnimalColors(3,"");
		colorsDao.insert(color);
		color = new AnimalColors(3,"");
		colorsDao.insert(color);
		color = new AnimalColors(3,"");
		colorsDao.insert(color);
		
		// rodent: id=4
		color = new AnimalColors(4,"");//animal type, color name
		colorsDao.insert(color);
		color = new AnimalColors(4,"");
		colorsDao.insert(color);
		color = new AnimalColors(4,"");
		colorsDao.insert(color);
		color = new AnimalColors(4,"");
		colorsDao.insert(color);
		color = new AnimalColors(4,"");
		colorsDao.insert(color);
		color = new AnimalColors(4,"");
		colorsDao.insert(color);
		color = new AnimalColors(4,"");
		colorsDao.insert(color);
		color = new AnimalColors(4,"");
		colorsDao.insert(color);
		color = new AnimalColors(4,"");
		colorsDao.insert(color);
		color = new AnimalColors(4,"");
		colorsDao.insert(color);
		
		// fish: id=5
		color = new AnimalColors(5,"");//animal type, color name
		colorsDao.insert(color);
		color = new AnimalColors(5,"");
		colorsDao.insert(color);
		color = new AnimalColors(5,"");
		colorsDao.insert(color);
		color = new AnimalColors(5,"");
		colorsDao.insert(color);
		color = new AnimalColors(5,"");
		colorsDao.insert(color);
		color = new AnimalColors(5,"");
		colorsDao.insert(color);
		color = new AnimalColors(5,"");
		colorsDao.insert(color);
		color = new AnimalColors(5,"");
		colorsDao.insert(color);
		color = new AnimalColors(5,"");
		colorsDao.insert(color);
		color = new AnimalColors(5,"");
		colorsDao.insert(color);
		
		// reptile: id=6
		color = new AnimalColors(6,"");//animal type, color name
		colorsDao.insert(color);
		color = new AnimalColors(6,"");
		colorsDao.insert(color);
		color = new AnimalColors(6,"");
		colorsDao.insert(color);
		color = new AnimalColors(6,"");
		colorsDao.insert(color);
		color = new AnimalColors(6,"");
		colorsDao.insert(color);
		color = new AnimalColors(6,"");
		colorsDao.insert(color);
		color = new AnimalColors(6,"");
		colorsDao.insert(color);
		color = new AnimalColors(6,"");
		colorsDao.insert(color);
		color = new AnimalColors(6,"");
		colorsDao.insert(color);
		color = new AnimalColors(6,"");
		colorsDao.insert(color);
		
		// amphibian: id=7
		color = new AnimalColors(7,"");//animal type, color name
		colorsDao.insert(color);
		color = new AnimalColors(7,"");
		colorsDao.insert(color);
		color = new AnimalColors(7,"");
		colorsDao.insert(color);
		color = new AnimalColors(7,"");
		colorsDao.insert(color);
		color = new AnimalColors(7,"");
		colorsDao.insert(color);
		color = new AnimalColors(7,"");
		colorsDao.insert(color);
		color = new AnimalColors(7,"");
		colorsDao.insert(color);
		color = new AnimalColors(7,"");
		colorsDao.insert(color);
		color = new AnimalColors(7,"");
		colorsDao.insert(color);
		color = new AnimalColors(7,"");
		colorsDao.insert(color);

		// Animal Breeds
		//dog: id=1
		AnimalBreeds breed = new AnimalBreeds(1,"Affenpinscher");//animal type, breed name
		breedsDao.insert(breed);
		breed = new AnimalBreeds(1,"Afghan Hound");
		breedsDao.insert(breed);
		breed = new AnimalBreeds(1,"Airedale Terrier");
		breedsDao.insert(breed);
		breed = new AnimalBreeds(1,"Akbash");
		breedsDao.insert(breed);
		breed = new AnimalBreeds(1,"Akita");
		breedsDao.insert(breed);
		breed = new AnimalBreeds(1,"Alaskan Malamute");
		breedsDao.insert(breed);
		breed = new AnimalBreeds(1,"American Bulldog");
		breedsDao.insert(breed);
		breed = new AnimalBreeds(1,"American Eskimo Dog");
		breedsDao.insert(breed);
		breed = new AnimalBreeds(1,"American Hairless Terrier");
		breedsDao.insert(breed);
		breed = new AnimalBreeds(1,"American Pit Bull Carrier");
		breedsDao.insert(breed);
		
		// cat: id=2
		breed = new AnimalBreeds(2,"Abyssinian");//animal type, breed name
		breedsDao.insert(breed);
		breed = new AnimalBreeds(2,"American Bobtail");
		breedsDao.insert(breed);
		breed = new AnimalBreeds(2,"American Curl");
		breedsDao.insert(breed);
		breed = new AnimalBreeds(2,"American Shorthair");
		breedsDao.insert(breed);
		breed = new AnimalBreeds(2,"American Wirehair");
		breedsDao.insert(breed);
		breed = new AnimalBreeds(2,"Balinese");
		breedsDao.insert(breed);
		breed = new AnimalBreeds(2,"Bengal");
		breedsDao.insert(breed);
		breed = new AnimalBreeds(2,"Birman");
		breedsDao.insert(breed);
		breed = new AnimalBreeds(2,"Bombay");
		breedsDao.insert(breed);
		breed = new AnimalBreeds(2,"British Shorthair");
		breedsDao.insert(breed);
		
		//Animal genders
		AnimalGender gender = new AnimalGender("Unknown");
		genderDao.insert(gender);
		gender = new AnimalGender("Male");
		genderDao.insert(gender);
		gender = new AnimalGender("Female");
		genderDao.insert(gender);
		
		
	}

}
