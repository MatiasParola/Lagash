package parquimetro;

public interface IControladorParquimetro {

    int CentavosPorHora = 70;//Precio estipulado pór hora
    
    void AutoDetectado(String Patente);
    void AvanzarMinuto();
    void EstacionamientoFinalizado();
    
}
