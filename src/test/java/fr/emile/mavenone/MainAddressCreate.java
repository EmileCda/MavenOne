package fr.emile.mavenone;

import java.util.List;

import fr.emile.mavenone.entity.Address;
import fr.emile.mavenone.entity.User;
import fr.emile.mavenone.model.dao.AddressDao;
import fr.emile.mavenone.model.dao.IAddressDao;
import fr.emile.mavenone.model.dao.IUserDao;
import fr.emile.mavenone.model.dao.UserDao;
import fr.emile.mavenone.utils.Utils;

public class MainAddressCreate {
	public MainAddressCreate() {
	}

	public static void main(String[] args) {
		final IAddressDao myAddressDao = new AddressDao();
		final IUserDao myUserDao = new UserDao();
		List<User> userList;
		Address myAddress = null;
		String bisTable[] = { "","","","","", " B", " b", " bis", " ter" };
		String typeStreetList[] = { "allée", "berges", "carrefour", "chaussée", "boulevard", "rue", "impasse",
				"passage", "place", "route", "ruelle", "sentier", "voie", "avenue" };
		String streetNameList[] = { "Charles Abel (député)", "Edmond About", "Amédée Achard", "Juliette Adam",
				"Paul Adam", "Jean-Joseph Ader", "Adolphe Aderer", "Marie d'Agoult", "Aimé Agussol", "Jean Aicard",
				"Étienne Aignan", "Louis Aigoin", "Gustave Aimard", "Jean Ajalbert", "Stéphane Ajasson de Grandsagne",
				"Célestin Albin de Cigala", "Albert Albrier", "Eugène Alcan", "Alexandre de Saint-Juan", "Paul Alexis",
				"Alphonse Allais", "Hortense Allart", "Édouard Alletz", "Edmond Alonnier", "Louise d'Alq",
				"Jean-Augustin Amar du Rivier", "Constant Améro", "Marie Améro", "Jean-Jacques Ampère",
				"Jean Barnabé Amy", "Jacques-François Ancelot", "Charles Andler", "Émile Andreoli",
				"Édouard d'Anglemont", "Théodore Anne", "Félix Ansart", "Thibaut d'Anthonay",
				"Antoine Antoine de Saint-Gervais", "Pierre Antoine Antonelle", "Paul Arbaud", "Louis Ardant",
				"Delphine Arène", "Blanche-Joséphine Le Bascle d'Argenteuil", "Léo Armagnac",
				"Marie Célestine Amélie d'Armaillé", "Arthur Arnould", "Edmond Arnould", "Paul Arosa", "Félix Aroux",
				"Achille d'Artois", "Claude d'Arvisenet", "Eugène Asse", "Louis Asseline", "Charles Asselineau",
				"Adolphe d'Assier", "Alfred Assollant", "Léon d'Astros", "Théodore Aubanel", "Constance Aubert",
				"Octave-Louis Aubert", "Paul Aubry (médecin)", "Xavier Aubryet", "Joseph Aude", "Philibert Audebrand",
				"Louis Audiat", "Jean-Marie-Vincent Audin", "Olympe Audouard", "Maurice-Ernest Audouin de Géronval",
				"Charles Auffret (médecin)", "Hippolyte Auger", "Victor Augier", "Gilbert Augustin-Thierry",
				"Aurel (écrivain)", "Gabriel-Albert Aurier", "Joseph Autran", "Félix Auvray", "Paul Avenel",
				"Adolphe d'Avril", "Zo d'Axa", "Alix Aylicson", "Pierre Hyacinthe Azaïs", "Georges Azéma" };

		String cityList[] = { "etercy", "chapdes beaufort", "les cheres", "chazey sur ain", "asnieres la giraud",
				"beauvais sur matha", "riom", "morance", "morance", "st priest palus", "bords", "luxe", "dorat",
				"ennezat", "ste feyre la montagne", "mionnay", "st remy sur durolle", "ste croix", "st front",
				"st jacques d ambur", "la villeneuve", "les houches", "enval", "st priest taurion", "st laure",
				"arconsat", "charbonnieres les varennes", "charbonnieres les varennes", "genay", "st claud", "neulise",
				"la clusaz", "bagnizeau", "chausseterre", "annecy", "tralaigues", "la brousse", "taillant", "aigre",
				"aigre", "st junien", "crevant laveine", "echillais", "echillais", "st brice sur vienne", "cleyzieu",
				"veyrac", "veyrac", "lovagny", "tarare", "licheres", "legny", "le chatenet en dognon",
				"st pardoux morterolles", "hauteville sur fier", "fontenet", "le monteil au vicomte", "meximieux",
				"sauviat sur vige", "valliere", "domancy", "miremont", "rilhac rancon", "st pierre bellevue",
				"st pierre bellevue", "les touches de perigny", "palladuc", "merinchal", "ranville breuillaud",
				"anglefort", "bagnols", "massieux", "st gence", "soubise", "cordon", "dolus d oleron", "st polgues",
				"lornay", "faramans", "la balme de thuy", "oradour", "barbezieres", "ventouse", "croizet sur gand",
				"fontenille", "moutonneau", "st froult", "lucenay", "chirassimont", "chirac", "les villards sur thones",
				"st bard", "quincieux" };
		try {
			userList = myUserDao.get();
			for (User oneUser : userList) {
				int maxAddress = Utils.randInt(1, 5);
				Utils.trace(String.format("%d", maxAddress));

				for (int index = 0; index < maxAddress; index++) {

					String numero = String.format("%d%s", Utils.randInt(1, 500),
							bisTable[Utils.randInt(0, bisTable.length - 1)]);
					String typeStreet = typeStreetList[Utils.randInt(0, typeStreetList.length - 1)];
					String streetName = streetNameList[Utils.randInt(0, typeStreetList.length - 1)];
					String zipCode = String.format("%05d", Utils.randInt(0, 99999));
					String city = cityList[Utils.randInt(0, cityList.length - 1)];
					city = Utils.firstToUpper(city);
					myAddress = new Address(numero, typeStreet + " " + streetName, city, zipCode, oneUser.getId());

					myAddressDao.add(myAddress);

				}

			}
			Utils.trace(myAddress.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.printf("Error add %s", myAddress);

		}

	}

}
