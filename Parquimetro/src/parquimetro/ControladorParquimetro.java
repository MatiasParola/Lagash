
package parquimetro;

//Importaciones de clases
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;


public class ControladorParquimetro implements IControladorParquimetro{

    //Declaracion de variables
    private String Patente;
    private int MinutosEstacionado;
    
    //Constructor de la clase Parquimetro
    public ControladorParquimetro(){
      
    }
    
    // getter and setter
    public void setPatente(String Patente){
        this.Patente = Patente;
    }
    public String getPatente(){
        return Patente;
    }
    public int getMinutosEstacionado(){
        return MinutosEstacionado;
    }
    public int getCentevosPorHora(){
        return CentavosPorHora;
    }
    
    

    //Metodos 
    @Override
    public void AutoDetectado(String Patente) {
     if (Patente != null){
           AvanzarMinuto();
       }else{
           EstacionamientoFinalizado();    
    }
    }
        

    @Override
    public void AvanzarMinuto() {
       Timer tiempo = new Timer();
       
       TimerTask min = new TimerTask(){
           @Override
           public void run(){
               MinutosEstacionado = MinutosEstacionado + 1;
           }
       };
       tiempo.schedule(min, 60000, 60000);
       
    }

    @Override
    public void EstacionamientoFinalizado() {
       int Horas = MinutosEstacionado/60;
       int TotalAPagar = 0;
        if (Patente != null){
                if ((MinutosEstacionado % 60) != 0){
               TotalAPagar = (Horas+1)*CentavosPorHora;            
                }   else if (MinutosEstacionado !=0) {
                    TotalAPagar = Horas*CentavosPorHora;
                }else{
                    TotalAPagar = CentavosPorHora;
                }
                }else {
            MinutosEstacionado = 0;
            Patente = null;
            }
        JOptionPane.showMessageDialog(null, "Patente : "+ Patente +" Minutos estacionado: "+ MinutosEstacionado); 
        JOptionPane.showMessageDialog(null, "Le corresponde Pagar: " + TotalAPagar);
        String email = ServicioExterno.ObtenerMailPorPatente(Patente);
        ServicioExterno.EnviarMail("Total a pagar por estar en nuestro parquimetro ", "Usted de debera pagar: "+ TotalAPagar, email);
        Patente = null;
        MinutosEstacionado = 0;
    }
    
    public void TiempoTranscurrido(){
        JOptionPane.showMessageDialog(null,"Su tiempo estacionado es : " + MinutosEstacionado);
    }
    
}
       