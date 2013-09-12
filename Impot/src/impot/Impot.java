package impot;

public class Impot 
{
	double[] coefficientsTranches;
	double [] debutsTranches;
	double abbatemment;
	
	public double getAbbatemment() 
	{
		return abbatemment;
	}

	public void setAbbatemment(double abbatemment) 
	{
		this.abbatemment = abbatemment;
	}

	public void init (int nbTranches)
	{
		coefficientsTranches = new double[nbTranches];
		debutsTranches = new double[nbTranches];
	}
	
	public void setTranche(int indiceTranche, double debutTranche, 
			double coefficientTranche)
	{
		debutsTranches[indiceTranche] = debutTranche;
		coefficientsTranches[indiceTranche] = coefficientTranche;
	}	
	
	public int getNbTranches()
	{	
		return coefficientsTranches.length;
	}
	
	public double getCoefficient(int i)
	{
		return coefficientsTranches[i];
	}
	
	public double getDebut(int i)
	{
		return debutsTranches[i];
	}
	
	public double montantImpot(double revenu)
	{
		double result = 0;
		for (int i = 0 ; i < getNbTranches() ; i++)
		{
			revenu -= (getDebut(i) - ((i > 0)? getDebut(i - 1): 0));
			if (revenu > 0)
				result += revenu * (getCoefficient(i) - 
					((i > 0)? getCoefficient(i - 1): 0));
			System.out.println("revenu = " + revenu + " result = " + result);
		}
		return result;
	}

	public double montantImpotAvecAbattements(double revenu)
	{
		return montantImpot(revenu * (1 - abbatemment));
	}
	
	public double montantImpotAvecAbattements(double revenu, int nombreDeParts)
	{
		return montantImpotAvecAbattements(revenu/nombreDeParts) * nombreDeParts;
	}
	
	public static Impot createImpot2013()
	{
		Impot impot = new Impot();
		impot.init(6);
		impot.setTranche(0, 0, 0);
		impot.setTranche(1, 5964, 0.055);
		impot.setTranche(2, 11897, 0.14);
		impot.setTranche(3, 26421, 0.3);
		impot.setTranche(4, 70831, 0.41);
		impot.setTranche(5, 150001, 0.45);
		impot.setAbbatemment(0.1);
		return impot;
	}

	public static void main(String[] args)
	{
		Impot i = createImpot2013(); 
		System.out.println(i.montantImpotAvecAbattements(60000, 2));
	}
}
