// PDOO.- Polimorfismo y ligadora dinámica
// Probando código.

import java.util.ArrayList;

class FiguraGeometrica{
    float area() {
        return 0.0f;
    }

    void interno(){
        System.out.println("Interno padre");
    }

    void metodo(){
        System.out.print("Voy a actuar: ");
        // Añadimos this. y sigue ejecutándose el del hijo cuando se llama a método desde el hijo.
        this.interno();
    }
    
    public static void main(String[] args) {

        // ---------------------------------
        // Probar si al hacer figura.area() llama al area de cada objeto o al del tipo de la lista.

        System.out.println("Main no compila");
        ArrayList<FiguraGeometrica> coleccion = new ArrayList<>();
        coleccion.add(new Circulo(5.0f));
        coleccion.add(new Rectangulo());

        float suma = 0.0f;
        for (FiguraGeometrica figura : coleccion){
            System.out.println(figura.area());
            suma += figura.area();
        }
        System.out.println(suma);

        // ---------------------------------
        // Cómo arreglar que un objeto de tipo FiguraGeometrica no puede acceder a la función decirHola() aunque por dentro sea del tipo hijo que si que tiene implementada esa función.
        
        FiguraGeometrica fig = new Circulo(5.0f);
        //fig.decirHola();
        ((Circulo)fig).decirHola();

        Circulo cir = new Circulo(5.0f);
        System.out.println(((FiguraGeometrica)cir).area());

        // ---------------------------------
        // Castear fi para que sea un circulo no se puede porque le falta info.
        // Castearlo a FiguraGeometrica no tiene sentido porque queremos igualarlo a un círculo.

        FiguraGeometrica fi = new FiguraGeometrica();
        //Circulo ci = (Circulo) fi;

        // -----------------------------------
        // Cambiando a self un método del padre que está sobreescrito: 
        // Al añadir el this sigue llamando al método del hijo, porque lo llama un objeto de tipo hijo, por tanto su this. es el del objeto hijo.

        FiguraGeometrica f = new FiguraGeometrica();
        Circulo c = new Circulo(1);

        f.metodo();
        c.metodo();

    }
}



class Circulo extends FiguraGeometrica{
    private float radio;

    Circulo(float r){
        radio = r;
    }
    float area(){
        float radio = 5.0f;
        float pi = 3.14f;
        return pi*radio*radio;
    }
    void decirHola(){
        System.out.println("HOLA" + radio);
    }
    void interno(){
        System.out.println("Interno hijo");
    }
}
class Rectangulo extends FiguraGeometrica{
    float area(){
        float lado1 = 5.0f;
        float lado2 = 4.0f;
        return lado1*lado2;
    }
}



