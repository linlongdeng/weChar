package weChat.json.jackson;

import java.util.ArrayList;
import java.util.List;

class Zoo {
    public String name;
    public String city;
 
     public Zoo(){
    	 
     }
    public Zoo( String name, String city) {
        this.name = name;
        this.city = city;
    }

    public List<Animal> animals = new ArrayList<Animal>();
 
    public List<Animal> addAnimal(Animal animal) {
        animals.add(animal);
        return animals;
    }

	@Override
	public String toString() {
		String str = "name:" + name + ",city" + city + ",animals:{";
		if(animals != null){
			for(Animal animal : animals){
				 str += animal +",";
			}
		}
		str += "}";
		return str;
	}
    
    
 
}