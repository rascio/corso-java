
public static void compra(Carrello carrello, CalcolaSconto calcolaSconto) {

  Float totale = 0;

  for (int i = 0 ; i < carrello.getElementi().length ; i++) {
    Item item = carrello.getElemento(i);
    totale = totale + item.getPrezzo();
  }

  totale = calcolaSconto.sconto(totale);
}

public interface CalcolaSconto {
  Float sconto(Float totale);
}

public class ScontoUtenteGold implements CalcolaSconto{
  public Float sconto(Float totale) {
    return totale * 0.5;
  }
}

public class ScontoNuovoUtente implements CalcolaSconto {
  public Float sconto(Float totale) {
    return totale * 0.9;
  }
}
