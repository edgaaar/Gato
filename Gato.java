/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package gato;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Edgar Saldaña Nieves
 */
public class Gato {
        public static ArrayList<Integer> tablero = new ArrayList<>(10); //Vector que representa el tablero(0 vacío, 1 o, 2 x)
        public static DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(tablero);
        
//Construye un tablero vacío        
public Gato(){
    tablero.clear();
    for(int i=0; i<=8; i++){
            tablero.add(0);
    } //El 0 significa que en esa casilla aún no se ha jugado.

    nodo.setUserObject(tablero);
    System.out.println("Tamaño del tablero: "+tablero.size());
}    
    //Obteiene la raíz, y la modifica para obtener una nueva raíz
    public void ModificaTablero(Integer posicion,Integer valor) {
        ArrayList<Integer> tableroAux = new ArrayList<>(10);
        //System.out.println(tablero);
        tableroAux = (ArrayList<Integer>) nodo.getUserObject();
        tableroAux.set(posicion,valor);
        
        //System.out.println(tablero);
        if(tableroAux.contains(0))
        CalculaMovimientos(tableroAux);
    }
    
    public void CalculaMovimientos(ArrayList<Integer> tablero){
        
        ArrayList<Integer> tableroAux = (ArrayList<Integer>) tablero.clone();
        ArrayList<Integer> indices = new ArrayList<>();
        DefaultMutableTreeNode nodoAux = new DefaultMutableTreeNode();
        
        /*System.out.println(tableroAux);
        System.out.println(tablero);
        *///El for genera todos los movimientos posibles y los asigna como tableros al nodo hijo
        for(int i = 0; i<=8; i++){
            tableroAux = (ArrayList<Integer>) tablero.clone();
            if(tableroAux.get(i) == 0){ //Si está vacío
                tableroAux.set(i, 2);//Ocupa el lugar con una x (En el Aux, que es una copia de tablero)
                DefaultMutableTreeNode nodoHijo = new DefaultMutableTreeNode(tableroAux); //
                nodo.add(nodoHijo); //Inserta un nodo por cada posibilidad de movimiento
            }  
        }
        System.out.println(nodo.getChildCount());
        indices.clear();
        
        //Calcula el índice p de todas las probables jugadas
        for(int i=0;i<nodo.getChildCount();i++){ 
            nodoAux = (DefaultMutableTreeNode) nodo.getChildAt(i);
            indices.add(CalculaP(nodoAux));
            //System.out.println(tableroAux);
        }
        
        int maximo = Collections.max(indices);//Almacena el índice máximo de la lista de índices P
        
        nodoAux = (DefaultMutableTreeNode) nodo.getChildAt(indices.indexOf(maximo));
        nodo = nodoAux;
        //Reflejo gráficamente el nuevo tablero
        //ActualizaTablero();
        System.out.println("Máximo: "+maximo);
    }

    
    //P es el índice de qué tan buena es la posición. Mayor P -> mejor posición.
    public static int CalculaP(DefaultMutableTreeNode nodoAux){
        Integer px = 0, po = 0;
        Integer p = 0;

        ArrayList<Integer> tableroAux = new ArrayList<>();
                
            tableroAux = (ArrayList<Integer>) nodoAux.getUserObject(); //Obtengo el tablero del 1er nodo hijo
            
            //Para el programa:
            //Verifica filas libres:
            if(tableroAux.get(0) != 1 && tableroAux.get(1)!=1 && tableroAux.get(2)!=1){
                px++;
                if(tableroAux.get(2)+tableroAux.get(1)+tableroAux.get(2)==6)
                    px+=3;
                
            }
            if(tableroAux.get(3) != 1 && tableroAux.get(4)!=1 && tableroAux.get(5)!=1){
                px++;
                if(tableroAux.get(3)+tableroAux.get(4)+tableroAux.get(5)==4)
                    px+=2;
                if(tableroAux.get(3)+tableroAux.get(4)+tableroAux.get(5)==6)
                    px+=30;
                
            }
            if(tableroAux.get(6) != 1 && tableroAux.get(7)!=1 && tableroAux.get(8)!=1){
                px++;
                if(tableroAux.get(6)+tableroAux.get(7)+tableroAux.get(8)==6)
                    px+=3;
            }
            
            //Verifica columnas libres:
            if(tableroAux.get(0) != 1 && tableroAux.get(3)!=1 && tableroAux.get(6)!=1){
                px++;
                if(tableroAux.get(0)+tableroAux.get(3)+tableroAux.get(6)==6)
                    px+=3;
            }
            if(tableroAux.get(1) != 1 && tableroAux.get(4)!=1 && tableroAux.get(7)!=1){
                px++;
                if(tableroAux.get(1)+tableroAux.get(4)+tableroAux.get(7)==4)
                    px+=2;
                if(tableroAux.get(1)+tableroAux.get(4)+tableroAux.get(7)==6)
                    px+=30;
            }
            if(tableroAux.get(2) != 1 && tableroAux.get(5)!=1 && tableroAux.get(8)!=1){
                px++;
                if(tableroAux.get(2)+tableroAux.get(5)+tableroAux.get(8)==6)
                    px+=3;
            }
            
            //Verifica diagonales:
            if(tableroAux.get(0) != 1 && tableroAux.get(4)!=1 && tableroAux.get(8)!=1){
                px++;
                if(tableroAux.get(0)+tableroAux.get(4)+tableroAux.get(8)==6)
                    px+=3;
            }
            if(tableroAux.get(2) != 1 && tableroAux.get(4)!=1 && tableroAux.get(6)!=1){
                px++;
                if(tableroAux.get(2)+tableroAux.get(4)+tableroAux.get(6)==6)
                    px+=3;
            }
            
            //Para el usuario:
            //Verifica filas libres:
            if(tableroAux.get(0) != 2 && tableroAux.get(1)!=2 && tableroAux.get(2)!=2){
                po++;
                if(tableroAux.get(0)+tableroAux.get(1)+tableroAux.get(2)==2)
                    po+=2;
            }
            if(tableroAux.get(3) != 2 && tableroAux.get(4)!=2 && tableroAux.get(5)!=2){
                po++;
                if(tableroAux.get(3)+tableroAux.get(4)+tableroAux.get(5)==2)
                    po+=2;
            }
            if(tableroAux.get(6) != 2 && tableroAux.get(7)!=2 && tableroAux.get(8)!=2){
                po++;
                if(tableroAux.get(6)+tableroAux.get(7)+tableroAux.get(8)==2)
                    po+=2;
            }
            
            //Verifica columnas libres:
            if(tableroAux.get(0) != 2 && tableroAux.get(3)!=2 && tableroAux.get(6)!=2){
                po++;
                if(tableroAux.get(0)+tableroAux.get(3)+tableroAux.get(6)==2)
                    po+=2;
            }
            if(tableroAux.get(1) != 2 && tableroAux.get(4)!=2 && tableroAux.get(7)!=2){
                po++;
                if(tableroAux.get(1)+tableroAux.get(4)+tableroAux.get(7)==2)
                    po+=2;
            }
            if(tableroAux.get(2) != 2 && tableroAux.get(5)!=2 && tableroAux.get(8)!=2){
                po++;
                if(tableroAux.get(2)+tableroAux.get(5)+tableroAux.get(8)==2)
                    po+=2;
            }
            
            //Verifica diagonales:
            if(tableroAux.get(0) != 2 && tableroAux.get(4)!=2 && tableroAux.get(8)!=2){
                po++;
               if(tableroAux.get(0)+tableroAux.get(4)+tableroAux.get(8)==2)
                    po+=2;
            }
            if(tableroAux.get(2) != 2 && tableroAux.get(4)!=2 && tableroAux.get(6)!=2){
                po++;
                if(tableroAux.get(2)+tableroAux.get(4)+tableroAux.get(6)==2)
                    po+=2;
            }
            
            p=px-po; //Indice p favorable al programa
            System.out.println(p);
        return p;
    }    
}

