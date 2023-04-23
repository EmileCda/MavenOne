package fr.emile.mavenone;

import fr.emile.mavenone.entity.User;
import fr.emile.mavenone.model.dao.IUserDao;
import fr.emile.mavenone.model.dao.UserDao;
import fr.emile.mavenone.utils.Utils;

public class MainUserCreate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final IUserDao myUserDao = new UserDao();
		int id = -1;
		String firstnameList[] = { "adélaďde", "adèle", "adrien", "aimé", "aimée", "alain", "albertine", "alexandrie",
				"alphonse", "amaury", "anatole", "antoine", "antoinette", "apollinaire", "armand", "arnaud", "aude",
				"auguste", "augustin", "aurélie", "aurore", "baptiste", "béatrice", "berthe", "camille", "carole",
				"cécile", "céleste", "chanté", "christophe", "claire", "clarisse", "claudine", "clément", "damien",
				"désiré", "dominique", "édouard", "élodie", "émile", "étienne", "eugène", "eugénie", "félix", "fernand",
				"franck", "françois", "françoise", "frédéric", "frédérique", "gaston", "geneviève", "georges", "gérard",
				"germain", "germaine", "gilberte", "gilles", "guillaume", "gustave", "hélène", "henri", "hervé",
				"hilaire", "honoré", "inès", "jacques", "jeanne", "jeannette", "jeannot", "jérémie", "jérôme", "joël",
				"jolie", "joséphine", "josette", "julien", "juliette", "juste", "laure", "laurent", "lazare", "léon",
				"léonard", "loup", "luc", "lucie", "lucien", "lucienne", "lucille", "marc", "marceline", "marguerite",
				"marie", "mariette", "marine", "mathilde", "michèle", "mignon", "modeste", "monique", "nadine",
				"nathalie", "nicolas", "odette", "olivier", "pascal", "paule", "philippe", "pierre", "raoul", "raphaël",
				"régis", "reine", "rené", "renée", "romain", "romaine", "rosette", "sébastien", "serge", "séverin",
				"solange", "stéphane", "suzanne", "sylvie", "thérèse", "thierry", "timothée", "urbain", "valéry",
				"véronique", "victoire", "vienne", "violette", "virginie" };

		String lastnameList[] = { "MARTIN", "BERNARD", "THOMAS", "ROBERT", "PETIT", "DUBOIS", "RICHARD", "GARCIA",
				"DURAND", "LEFEBVRE", "MOREAU", "SIMON", "LAURENT", "LEROY", "MICHEL", "FONTAINE", "DA SILVA", "DAVID",
				"MARTINEZ", "MOREL", "DUPONT", "LAMBERT", "FOURNIER", "GIRARD", "BERTRAND", "LEFEVRE", "ROUSSEAU",
				"ROUX", "VINCENT", "FRANCOIS", "BOYER", "LEGRAND", "ANDRE", "MULLER", "CHEVALIER", "BONNET", "GUERIN",
				"LOPEZ", "MERCIER", "GARNIER", "SANCHEZ", "HENRY", "PEREZ", "PEREIRA", "FAURE", "FERREIRA", "ROBIN",
				"CLEMENT", "GAUTIER", "GAUTHIER", "PAYET", "DUVAL", "MORIN", "BLANC", "ROUSSEL", "PERRIN", "FERNANDEZ",
				"NICOLAS", "MASSON", "RIVIERE", "MARIE", "LEMAIRE", "NOEL", "MATHIEU", "DENIS", "DOS SANTOS", "LUCAS",
				"MARCHAND", "NGUYEN", "DUMONT", "DUFOUR", "RODRIGUES", "RENARD", "JOLY", "MEUNIER", "CARON",
				"FERNANDES", "MEYER", "RODRIGUEZ", "BARBIER", "BLANCHARD", "GERARD", "GONCALVES", "LEROUX", "SCHMITT",
				"PIERRE", "BRUNET", "COLIN", "LEMOINE", "PICARD", "ROGER", "BRUN", "GAILLARD", "ROY", "GIRAUD",
				"OLIVIER", "ARNAUD", "VIDAL" };

		User myUser = null;
		try {
			
			for(int index = 0 ; index < 20 ; index ++) {
				String firsname = firstnameList[Utils.randInt(0, firstnameList.length - 1)];
				firsname = Utils.firstToUpper(firsname);
				String lastname = lastnameList[Utils.randInt(0, lastnameList.length - 1)];
				lastname = Utils.firstToUpper(lastname);
				int day = Utils.randInt(1, 31);
				int month = Utils.randInt(1, 12);
				int year = Utils.randInt(1905, 2005);
				String DateString = String.format("%02d%02d%04d", day, month, year);
	
				myUser = new User(firsname, lastname, Utils.string2Date(DateString, "ddMMyyyy"));
				id = myUserDao.add(myUser);
				myUser.setId(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.printf("Error add %s", myUser);

		} finally {
			System.out.printf("%s", myUser);

		}
	}


}
