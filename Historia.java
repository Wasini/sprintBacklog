
public class Historia {

    private int costo;
    private int valor;
    private String desc;



    public Historia(int costo,int valor,String desc) {
        this.costo = costo;
        this.valor = valor;
        this.desc = desc;
        }


    @Override
    public String toString() {
        return "Historia{" +
                "costo=" + costo +
                ", valor=" + valor +
                ", desc='" + desc + '\'' +
                '}';
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getDesc() {

        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }



}
