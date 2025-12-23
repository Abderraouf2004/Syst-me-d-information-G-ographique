/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sig_tp;
import java.util.*;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.io.*;


import javax.swing.JFrame;
import javax.swing.JPanel;







public class present_cart extends JFrame {
	   
	   String fichier_seq= present_cart.lire_string("nom de fichier:");
          static String fichier_arets= present_cart.lire_string("nom de fichier:");
	   LinkedList list_point;
           static LinkedList list_fichier_noeud ;
           static LinkedList list_fichier_arets;
	   JPanel jp= new JPanel();
	   static String separateur= ",";
	   static int norm_valu=20;
	   static int limitor_valu=-1;
	   
	   JPanel jPanel1 = new JPanel(){
	         public void paintComponent(Graphics g){
	                super.paintComponent(g);
	                present_map(g);
	                g.drawLine(10,20,30,40);
	                }
	   
	              };




	   public present_cart(String lien_fichier){
		      //super();
		      this.setTitle("Fenêtre à tester");
		      this.setSize(200, 300);
		      this.setContentPane(jPanel1);
                       //this.getContentPane().add(jPanel1);
		      //this.add(jPanel1);
	          }
	   
	   
	   
	   
	         
	   public void present_map(Graphics G){
	               //Graphics G = jPanel1.getGraphics();
	               this.list_point=present_cart.charger_fichier_list(this.fichier_seq);
//	               present_cart.affich_list(list_point);
	               double[] tab_point1=(double[])list_point.get(0);
	               int[] tab_int1= present_cart.nomaliser_table(tab_point1);                
	               for(int i =0;i<this.list_point.size();i++){
	            	   double[] tab_point2=(double[])list_point.get(i);
	            	   int[] tab_int2= present_cart.nomaliser_table(tab_point2);
	            	   if(tab_point2[0]!= present_cart.limitor_valu){
	            		   G.drawLine(tab_int1[0], tab_int1[1],tab_int2[0], tab_int2[1]);
	            		   tab_int1=tab_int2;
	            	       }
	                   }
		   
	              }      
	   
	   public static int[] nomaliser_table(double[] table){
		      int[] tab_int= new int[table.length];
		      for(int i =0; i<table.length;i++){
		    	  if(tab_int[i]!=present_cart.limitor_valu)
		    	  tab_int[i]= (int)(table[i]*present_cart.norm_valu); 
		          }
		       return tab_int;
	          }
	   
	   
       public static LinkedList charger_fichier_list(String url_fichier){
    	      LinkedList list_point = new LinkedList();
    	      
    	      try{
    	    	  BufferedReader br= new BufferedReader (new FileReader(url_fichier));
    	    	  String line ="";
    	    	  
    	    	  while((line=br.readLine())!= null){
    	    		    String[] tab_point_st= line.split(separateur);
    	    		    double[] tab_point= new double[tab_point_st.length];
    	    		    for(int i =0; i<tab_point.length;i++){
    	    		    	tab_point[i]=Double.parseDouble(tab_point_st[i].trim());
    	    		       }
    	    		    list_point.add(tab_point);
                             }
    	    	  
    	    	  
    	         }
    	      
    	      catch(Exception e){System.out.println("il y a un problème de lexture du fichier text "+e.getMessage());}
    	      return list_point;
              }
	   public static String lire_string(String m){
		      System.out.println(m);
		      String t=null;
		      try{
		    	 BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		    	 t=br.readLine();
		         }
		      catch(Exception e){System.out.println(e.getMessage());}
		      return t;
	          }
	   
	   public static double lire_double(String m){
		      double t = Double.parseDouble(present_cart.lire_string(m));
		      return t;
		   
	          }
	   
	 //Overridden so we can exit when window is closed
	   protected void processWindowEvent(WindowEvent e) {
	     super.processWindowEvent(e);
	     if (e.getID() == WindowEvent.WINDOW_CLOSING) {
	       System.exit(0);
	     }
	   }
        public static void main (String[] arg){
        	   String lien_fichier= present_cart.lire_string("file url :");
        	   present_cart pc= new present_cart(lien_fichier);
                   LinkedList list_point= present_cart.charger_fichier_list(lire_string("uri_fichier"));
                   pc.list_point = list_point;
                   pc.setVisible(true);
        	   pc.repaint();


//                    int nbr_point = pc.cal_nbr_point(list_point);
//                    System.out.println("nombre total de point est: " + nbr_point);
//                    int nbr_seg = pc.cal_nbr_semg();
//                    System.out.println("nombre total de segments est: " + nbr_seg);
//                    
//                    peremetre_zone(list_point);
//                    
//                    
//                    double peremetre_cart = pc.peremetre_carte(list_point);
//                    System.out.println("Le peremetre de la carte  est: " + peremetre_cart);
//                    
//                    surface_zone(list_point);
//                    liste_noeuds(list_point);
//                    liste_arets(list_point);
//                   Dime_seqeunce(f_A);
                    liste_noeuds_polyvrt(list_point);
                    point_intermediaire(list_point);
                    pointeure_chaines(list_point);
                    liste_ch(list_point);
                    
         
               } 
	    public static void affich_list(LinkedList list_point){
		      System.out.println("affichage de la list des éléments du fichier");
		      int size_list= list_point.size();
		      int i=0;
		      for(i=0; i<size_list; i++ ){
		    	  String t ="";
                           double[] tab_double = (double[]) list_point.get(i);
		    	  int tail_table=tab_double.length;
		    	  int j=0;
		    	  for(j=0;j<tail_table;j++){
		    		 t =t+","+ tab_double[j];
		    	     }
		    	  System.out.println(t);
		    	  
		          }
		   
	    }
	     LinkedList<double[]> segments = new LinkedList<>();
	     public int cal_nbr_semg(){
		     int nbr_seg =0;
		      for(int i =0 ; i<this.list_point.size();i++){
		    	 if(i< this.list_point.size()-1) {
		    	 double[] p_1 = (double[]) this.list_point.get(i);
		    	 double[] p_2 =  (double[]) this.list_point.get(i+1);
		    	 if (p_1[0]!=-1 & p_1[1]!=-1 & p_2[0]!=-1 & p_2[1]!=-1){
                             if (!recherche_segment(p_1,p_2,i)) {
                              nbr_seg++;
                             } 
   
		    	  }	    	 
	                 }
                    
		         }                       
                               
		      return nbr_seg;     
	     }
           
                public boolean recherche_segment(double[] p1,double[] p2,int index){
                    
                    int j;
                    
                         for(j =0 ; j<index;j++){
                            if(j< this.list_point.size()-1) {
		    	 double[] p_1 = (double[]) this.list_point.get(j);
		    	 double[] p_2 =  (double[]) this.list_point.get(j+1);
		    	 if (p_1[0]!=-1 & p_1[1]!=-1 & p_2[0]!=-1 & p_2[1]!=-1){
                             if ((p1[0]==p_1[0] && p1[1]==p_1[1]&& p2[0]==p_2[0]&& p2[1]==p_2[1])
                                     || (p1[0]==p_2[0] && p1[1]==p_2[1]&& p2[0]==p_1[0]&& p2[1]==p_1[1])) {
                              return true;
                             } 
   
		    	  }	    	 
	                 }
                             
                        }
                    return false;
                }
           
           
                public  int cal_nbr_point(LinkedList<double[]> list_point){
		     int nbr_point =0;
		      for(int i =0 ; i<list_point.size();i++){
		    	 if(i< list_point.size()-1) {
		    	 double[] p_1 = (double[]) list_point.get(i);
                        if (p_1[0]!=-1 & p_1[1]!=-1){       
                           if (!recherche_point(p_1,i)) {
                              nbr_point++;
                           }
		        }
                        }
                    
		         }                       
          
		      return nbr_point;     
	          }
                
                public boolean recherche_point(double[] p,int index){
                    
                    int j;
                    
                         for(j =0 ; j<index;j++){
                            double[] p_2 = (double[]) this.list_point.get(j);
		    	   
                            if (p[0]==p_2[0] && p[1]==p_2[1]){
                                 return true;
		    	    } 
                             
                        }
                    return false;
                }
                
                public static double distance(double[] p1,double[] p2){
                    
                    return Math.sqrt(Math.pow(p2[0]- p1[0],2) + Math.pow(p2[1]- p1[1],2));
                }
                
                public static void peremetre_zone(LinkedList<double[]> list_point){
                 double c = 0;
                 int a = 1;                 
                    for(int i =0 ; i<list_point.size();i++){
		      if(i< list_point.size()-1) {
                         double[] p1 = (double[]) list_point.get(i);
                         double[] p2 = (double[]) list_point.get(i+1);
                       if((p1[0] == -1 && p1[1] == -1) || (p2[0] == -1 && p2[1] == -1)){
                          if(c>0){
                           System.out.println("Le peremetre de la zone " + a + " est " + c);
                           a++;
                          }
                          c = 0;
                    
                        }
                        if(!(p1[0] == -1 && p1[1] == -1) && !(p2[0] == -1 && p2[1] == -1)){
                         c += distance(p1, p2);
                        }
                      }
                    
		    }     
                }
                
                
                
                
                
                public  double peremetre_carte(LinkedList<double[]> list_point){
                 double p = 0;                
                    for(int i =0 ; i<list_point.size();i++){
		      if(i< list_point.size()-1) {
                         double[] p1 = (double[]) list_point.get(i);
                         double[] p2 = (double[]) list_point.get(i+1);
                         if(nbr_occ_segment(p1,p2)==1){
                              p += distance(p1, p2); 
                         }          
                      }  
		    }
                    return p; 
                }
                
                public int nbr_occ_segment(double[] p1,double[] p2){                   
                        int occ=0;
                        for(int i =0 ; i<list_point.size();i++){
                            if(i< this.list_point.size()-1) {
		    	     double[] p_1 = (double[]) this.list_point.get(i);
		    	     double[] p_2 =  (double[]) this.list_point.get(i+1);
		    	 if (p_1[0]!=-1 & p_1[1]!=-1 & p_2[0]!=-1 & p_2[1]!=-1){
                             if ((p1[0]==p_1[0] && p1[1]==p_1[1]&& p2[0]==p_2[0]&& p2[1]==p_2[1])
                                     || (p1[0]==p_2[0] && p1[1]==p_2[1]&& p2[0]==p_1[0]&& p2[1]==p_1[1])) {
                                occ++;
                             } 
		    	  }	    	 
	                 }     
                        }
                    return occ;
                }
                
                
                
                public static void surface_zone(LinkedList<double[]> list_point){
                 double A = 0; 
                 int z=1;
                    for(int i =0 ; i<list_point.size();i++){
		      if(i< list_point.size()-1) {
                         double[] p1 = (double[]) list_point.get(i);
                         double[] p2 = (double[]) list_point.get(i+1);
                       if((p1[0] == -1 && p1[1] == -1) || (p2[0] == -1 && p2[1] == -1)){
                          if(A>0){
                              double S = A*0.5;
                           System.out.println("Le surface de la zone " + z + " est " + S);
                           z++;
                          }
                          A = 0;
                    
                        }
                        if(!(p1[0] == -1 && p1[1] == -1) && !(p2[0] == -1 && p2[1] == -1)){
                         A += (p1[0]*p2[1])-(p2[0]*p1[1]);
                        }
                      }
                    
		    }     
                }
                
               
                
                static  LinkedList f_n = new LinkedList();
                public static void liste_noeuds(LinkedList<double[]> list_point){
                    int id=1;
    	         
    	            for(int i =0 ; i<list_point.size();i++){
		      if(i< list_point.size()-1) {
                        double[] p = (double[]) list_point.get(i);
                         if((p[0] != -1 && p[1] != -1)){
                           if(!point_existe(f_n,p)){
                               double[] noeud = new double[3];
                               noeud[0] = id;     
                               noeud[1] = p[0];   
                               noeud[2] = p[1];   
                               f_n.add(noeud);
                               id++; 
                           }                   
                        }                  
                      }   
		    }
                    fichier_noeuds(f_n,"fichier_noeuds.txt");
                   
                }
                
                public static void fichier_noeuds(LinkedList<double[]> f_n, String nomFichier) {
                 try {
                    FileWriter fw = new FileWriter(nomFichier);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write("id\tX\tY");
                    bw.newLine();
                    for (int i = 0; i < f_n.size(); i++) {
                     double[] noeud = f_n.get(i);
                     String ligne = (int) noeud[0] + "\t" + noeud[1] + "\t" + noeud[2];
                     bw.write(ligne);
                     bw.newLine();
                    }
                    bw.close();
                    fw.close();
                   System.out.println("Fichier de nœuds créé : " + nomFichier);

                 } catch (Exception e) {
                  System.out.println("Erreur lors de l'écriture du fichier : " + e.getMessage());
                 }
                }
                
                public static void fichier_arets(LinkedList<int[]> f_A, String nomFichier) {
                   try {
                    FileWriter fw = new FileWriter(nomFichier);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write("id\tN_depart\tN_arrive\tZ_gauche\tZ_droit");
                    bw.newLine();
        
                    for (int i = 0; i < f_A.size(); i++) {
                      int[] aret = f_A.get(i);
                      String ligne = (int) aret[0] + "\t" + (int) aret[1] + "\t" + (int) aret[2]+ "\t" + (int) aret[3] + "\t" + (int) aret[4];
                      bw.write(ligne);
                      bw.newLine();
                    }
        
                    bw.close();
                    fw.close();
                    System.out.println("Fichier d'arêtes créé : " + nomFichier);

                    } catch (Exception e) {
                     System.out.println("Erreur lors de l'écriture du fichier : " + e.getMessage());
                    }
                }


                
                
                
                
                
                public static boolean point_existe(LinkedList<double[]> f_n,double[] p){
                    
                        for(int i =0 ; i<f_n.size();i++){
		            if(i< f_n.size()-1) {
                               double[] p_f_n = (double[]) f_n.get(i); 
                               if((p[0] ==  p_f_n[1])  && (p[1] ==  p_f_n[2])){
                                  return true; 
                               }                  
                            }               
		        }
                     return false;
                }
                
                static  LinkedList f_A = new LinkedList();
                
                public static void liste_arets(LinkedList<double[]> list_point){
                   
                     
    	            for(int i =0 ; i<list_point.size();i++){
		      if(i< list_point.size()-1) {
                        double[] p1 = (double[]) list_point.get(i);
                        double[] p2 = (double[]) list_point.get(i+1);
                        if((p1[0] != -1 && p1[1] != -1) || (p2[0] != -1 && p2[1] != -1)){
                          globale(p1,p2);                  
                        }                  
                      }   
		    }
                    fichier_arets(f_A, "fichier_arets.txt"); 
                }
                
                
                
                
               
    	       
                static int id_C=1;
                public static void globale(double[] p1,double[] p2){
                   
                   
                   int N_depart= id_noeud(p1[0],p1[1]);
                   int N_arrive= id_noeud(p2[0],p2[1]);
                           int[] aret = new int[5];
                           if(!recherche_C(N_depart,N_arrive) && (N_depart != -1) && (N_arrive != -1)){
                              
                               aret[0] = id_C;     
                               aret[1] = N_depart;   
                               aret[2] = N_arrive;
                               aret[3] = 1;
                               f_A.add(aret);
                               id_C++;
                                
                           } 
                           
                           

                  
                }

                
                
                
                public static int id_noeud(double x,double y){
                  
                    for(int i =0 ; i<f_n.size();i++){
		            if(i< f_n.size()) { 
                               double [] p_n = (double[]) f_n.get(i);
                               if((x ==  p_n[1])  && (y ==  p_n[2])){
                                  return (int) p_n[0]; 
                               }                  
                            }               
		    }
                    return -1;
                }
                
                
                
                public static double [] coordonnee_noeud(int id){
                
                    for(int i =0 ; i<f_n.size();i++){
		            if(i< f_n.size()) { 
                               double[] p_n = (double[]) f_n.get(i);
                               if(id == p_n[0]){
                                  double x = p_n[1];
                                  double y = p_n[2];
                                  return new double[]{x,y}; 
                               }                  
                            }               
		    }
                   return null;
                }
              
                
                
                
                public static boolean recherche_C(double N_depart,double N_arrive){
                    for(int i =0 ; i<f_A.size();i++){
		        if(i< f_A.size()) {
                            int[] C = (int[]) f_A.get(i); 
                            if ( (N_depart == C[1] && N_arrive == C[2])|| (N_depart == C[2] && N_arrive == C[1])) {
                                 C[4]=1;
                                 return true;
                             }                                
                        }               
		    }
                    return false;
                }
                
                static LinkedList<double[]> f_S = new LinkedList<>();
                
                
                public static void  Dime_seqeunce(LinkedList<int[]> f_A){
                   int debut = f_A.get(0)[1];
                   boolean change=false;
                   int[] A=new int[5];
                    for(int i =0 ; i<f_A.size();i++){
		      if(i< f_A.size()) {
                        int[] C = (int[]) f_A.get(i); 
                        if(i>0){
                          A = (int[]) f_A.get(i-1);
                        }
                         
                        if(verife(C[2],debut,i)){
                           double[] p = coordonnee_noeud(debut);
                           f_S.add(p);
                           f_S.add(new double[]{-1, -1});
                           change=true;
                        }
                         if(change){
                          debut=C[1];  
                        }
                        double[] p1 = coordonnee_noeud(C[1]); 
                        double[] p2 = coordonnee_noeud(C[2]);
                        if(C[1] != A[2]){
                          f_S.add(p1);
                        }
                        f_S.add(p2);
                         if(debut == C[2]){                       
                           f_S.add(new double[]{-1, -1});
                           debut=C[1];
                        }
                                                            
                      }   
		    }

                    fichier_s(f_S,"seqeunce.txt");
                }  
                
                
                public static boolean verife(int N_arrive,int debut,int index){
                     for(int i =0 ; i<index;i++){
		      if(i< f_A.size()) {
                        int[] C = (int[]) f_A.get(i); 
                        if( N_arrive == C[2] ){
                           if( debut == C[1] ){
                              return true;
                           }
                        }
                      }   
		    }
                     return false;
                }
                
                
                
                
                    
                public static void fichier_s(LinkedList<double[]> f_S, String nomFichier) {
                   try {
                    FileWriter fw = new FileWriter(nomFichier);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write("X,Y");
                    bw.newLine();
        
                    for (int i = 0; i < f_S.size(); i++) {
                      double[] s = f_S.get(i);
                      String ligne = (int) s[0] + "," + (int) s[1] ;
                      bw.write(ligne);
                      bw.newLine();
                    }
        
                    bw.close();
                    fw.close();
                    System.out.println("Fichier de seqeunce créé : " + nomFichier);

                    } catch (Exception e) {
                     System.out.println("Erreur lors de l'écriture du fichier : " + e.getMessage());
                    }
                }
                
                
                    
              
                static  LinkedList f_n_polyvrt = new LinkedList();
                public static void liste_noeuds_polyvrt(LinkedList<double[]> list_point){
                    int id=1;
    	         
    	            for(int i =1 ; i<list_point.size();i++){
		      if(i< list_point.size()-1) {
                            if(i==0){
                               double[] p1 = (double[]) list_point.get(i);
                               double[] p2= (double[]) list_point.get(i+1);
                             if(is_noeud_spesiale(p1,p2,0,list_point)>2){
                                double[] noeud = new double[3];
                                noeud[0] = id;     
                                noeud[1] = p1[0];   
                                noeud[2] = p1[1];   
                                f_n_polyvrt.add(noeud);
                                id++;
                               }  
                           }else{
                                if(i==list_point.size()-1){
                                  double[] p1 = (double[]) list_point.get(i);
                                  double[] p2= (double[]) list_point.get(i-1);
                                  if(is_noeud_spesiale(p1,p2,0,list_point)>2){
                                   double[] noeud = new double[3];
                                   noeud[0] = id;     
                                   noeud[1] = p1[0];   
                                   noeud[2] = p1[1];   
                                   f_n_polyvrt.add(noeud);
                                   id++;
                                 }  
                                }else{
                                           
                                 double[] p1 = (double[]) list_point.get(i-1);
                                 double[] p2 = (double[]) list_point.get(i);
                                 double[] p3 = (double[]) list_point.get(i+1);
                                if((p1[0] != -1 && p1[1] != -1)&&(p2[0] != -1 && p2[1] != -1)&&(p3[0] != -1 && p3[1] != -1)){
                                 if(is_noeud(p1,p2,p3,i,list_point)){
                                  double[] noeud = new double[3];
                                  noeud[0] = id;     
                                  noeud[1] = p2[0];   
                                  noeud[2] = p2[1];   
                                  f_n_polyvrt.add(noeud);
                                  id++;
                               }                    
                              } 
                                }
                            }
                                       
                      }   
		    }
                    fichier_noeuds(f_n_polyvrt,"fichier_noeuds_polyfrt.txt");
                   
                }
                
                public static boolean is_noeud(double[] p1,double[] p2,double[] p3,int index,LinkedList<double[]> list_point){
                    for(int i =index + 1 ; i<list_point.size();i++){
		      if(i< list_point.size()-2) {
                        double[] p = (double[]) list_point.get(i);
                        if((p[0] == p2[0] && p[1] == p2[1])){
                            double[] p11 = (double[]) list_point.get(i-1);
                            double[] p22 = (double[]) list_point.get(i+1);
                            if(((p1[0] != p11[0] || p1[1] != p11[1])&& (p11[0] !=-1))
                               || ((p1[0] != p22[0] || p1[1] != p22[1])&& (p22[0] !=-1))
                               || ((p2[0] != p11[0] || p2[1] != p11[1])&& (p11[0] !=-1))
                               || ((p2[0] != p22[0] || p2[1] != p22[1])&& (p22[0] !=-1))){
                             return true;                           
                            }
                        }                  
                      }   
		    }
                    return false;                   
                }
                
                
                
                   public static int is_noeud_spesiale(double[] p1,double[] p2,int index,LinkedList<double[]> list_point){
                       int c=1;
                    for(int i =index + 1 ; i<list_point.size();i++){
		      if(i< list_point.size()-1) {
                        double[] p = (double[]) list_point.get(i);
                        if((p[0] == p1[0] && p[1] == p1[1])){
                            double[] p11 = (double[]) list_point.get(i-1);
                            double[] p22 = (double[]) list_point.get(i+1);
                            if(((p2[0] != p11[0] || p2[1] != p11[1])&& (p11[0] !=-1))
                               ){
                             c++;                           
                            }
                            if(((p2[0] != p22[0] || p2[1] != p22[1])&& (p22[0] !=-1))
                               ){
                             c++;                           
                            }
                        }                  
                      }   
		    }
                    return c;                   
                }
                   
                   
                   
                static LinkedList<Object[]> f_point_intermediaire = new LinkedList<>();                
                public static void point_intermediaire(LinkedList<double[]> list_point){
                    int c=1;
                        for(int i =0 ; i<list_point.size();i++){
		            if(i< list_point.size()-1) {
                               double[] p = (double[]) list_point.get(i); 
                                   if (p[0] != -1 && p[1] != -1 && exist_neoud(p)) {
                                        LinkedList<double[]> res =  chaine(list_point, i);
                                         if (!res.isEmpty()) {
                                         Object[] ligne = new Object[2];
                                         ligne[0] = "C" + c;
                                         ligne[1]=res;
                                        f_point_intermediaire.add(ligne);
                                        c++;
                                        
}
                                    }
                            }               
		        }
                        ecrire_f_point_intermediaire(f_point_intermediaire, "f_point_intermediaire.txt");

              
                }
                
                
               public static LinkedList<double[]>  chaine(LinkedList<double[]> list_point,int index){
                    LinkedList<double[]> points_chaine = new LinkedList<>();
                      for (int i = index + 1; i < list_point.size(); i++) {
                          double[] p =(double[]) list_point.get(i);
                          if (p[0] != -1 && p[1] != -1) {
                              if (!exist_neoud(p)) {
                               points_chaine.add(p);
                              }else{
                                   return points_chaine;
                              }
                              
                          }
                        
                      }               
		    return points_chaine;     
                }


public static void ecrire_f_point_intermediaire(
        LinkedList<Object[]> f_point_intermediaire,
        String nomFichier) {

    BufferedWriter bw = null;

    try {
        bw = new BufferedWriter(new FileWriter(nomFichier));

        for (Object[] ligne : f_point_intermediaire) {

            String etiquette = (String) ligne[0];
            LinkedList<double[]> points =
                    (LinkedList<double[]>) ligne[1];

            // écrire l'étiquette
            bw.write(etiquette + " : ");

            // écrire les points
            for (double[] p : points) {
                bw.write("(" + p[0] + "," + p[1] + ") ");
            }

            bw.newLine();
        }

    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            if (bw != null) bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





                
                
                
                
                
                
               
                
                public static boolean exist_neoud(double[] p){
                    
                        for(int i =0 ; i<f_n_polyvrt.size();i++){
		            if(i< f_n_polyvrt.size()) {
                               double[] N = (double[]) f_n_polyvrt.get(i); 
                                if(N[1]==p[0] && N[2]==p[1]){
                                   return true;
                                }                 
                            }               
		        }
                   return false;
                    
                }
           
                public static int id_exist_neoud(double[] p){
                    
                        for(int i =0 ; i<f_n_polyvrt.size();i++){
		            if(i< f_n_polyvrt.size()) {
                               double[] N = (double[]) f_n_polyvrt.get(i); 
                                if(N[1]==p[0] && N[2]==p[1]){
                                   return (int) N[0];
                                }                 
                            }               
		        }
                   return -1;
                    
                }
                
                
                
               
                
                
                
               
                static  LinkedList f_zones  = new LinkedList();
                public static void pointeure_chaines(LinkedList<double[]> list_point){
                       
                    LinkedList<double[]> res = liste_chaines_zone(list_point); 
//                    liste_ch(f_zones,list_point);
                    fichier_zones(f_zones , "f_pointeure_chaines.txt"); 
 
                }
                
                
                static  LinkedList<double[]>  liste_chaines_zone(LinkedList<double[]> list_point){
                    LinkedList<double[]> list_chaine = new LinkedList<>();
                    for (int i = 0 ; i < list_point.size(); i++) {
                        double[] p = list_point.get(i);
                           if (p[0] != -1 && p[1] != -1) {
                              if (exist_neoud(p)) {
                               int idDepart = id_exist_neoud(p);
                               int idArrive=id_arrive(list_point,i);
                                if(!exist_chaine(idDepart,idArrive,list_chaine) && idArrive!=-1){
                                    double[] chaine = new double[2];
                                    chaine[0] = idDepart;     
                                    chaine[1] = idArrive;     
                                    list_chaine.add(chaine);
                                }
                                
                              }
                            
                          }else{
                              if (!list_chaine.isEmpty()) {
                                  f_zones.add(list_chaine);
                                  list_chaine=new LinkedList<>();
                              }  
                          }                                               
                    }               
		    return f_zones;   
                }
                
                
                
                static  int  id_arrive(LinkedList<double[]> list_point,int index){
                    for (int i = index+1 ; i < list_point.size(); i++) {
                        double[] p = list_point.get(i);
                           if (p[0] != -1 && p[1] != -1) {
                              if (exist_neoud(p)) {
                               return id_exist_neoud(p);   
                              }
                           }else{
                               return -1; 
                           }
                          
                    }               
		   return -1;     
                }
                
                
                 
                static  boolean  exist_chaine(int idDepart,int idArrive, LinkedList<double[]> list_chaine){
                    for (int i = 0 ; i < list_chaine.size(); i++) {
                        double[] C = list_chaine.get(i);
                           if ((C[0] == idDepart && C[1] == idArrive)||(C[0] ==idArrive  && C[1] == idDepart)) {
                              return true;
                           }
                          
                    }               
		   return false;     
                }
                
                
                
                
                
                public static void fichier_zones(LinkedList<LinkedList<double[]>> zones, String nomFichier) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomFichier))) {

        int numZone = 1;
        for (LinkedList<double[]> zone : zones) {
            bw.write("Zone " + numZone);
            bw.newLine();

            for (double[] chaine : zone) {
                // chaine[0] = idDepart, chaine[1] = idArrive
                bw.write("C" + (int) chaine[0] + " -> " + (int) chaine[1]+"|");
               
            }

            bw.newLine(); // séparation entre zones
            numZone++;
        }

        System.out.println("Fichier " + nomFichier + " créé avec succès.");

    } catch (IOException e) {
        e.printStackTrace();
    }
                  
     
                }   
    
    
    
    
    
       

                
                 static LinkedList<double[]> f_chaine = new LinkedList<>();                
                public static void liste_ch(LinkedList<double[]> list_point){
                   int id=1;
                     for (int i = 0 ; i < list_point.size(); i++) {
                        double[] p =(double[]) list_point.get(i);
                           if (p[0] != -1 && p[1] != -1) {
                              if (exist_neoud(p)) {
                               double idDepart =(double) id_exist_neoud(p);
                               double[] F = f_c(list_point,i);
                              if (F != null &&  !recherche2(idDepart, F[1],F[0], f_chaine) && F[1] != -1) {
                                   double[] C = new double[6];
                                   C[0]=id;
                                   C[1]=F[0];
                                   C[2]=idDepart;
                                   C[3]=F[1];
                                   C[4]=1;
                                   f_chaine.add(C);
                                   id++;
                               }
                                
                              }
                            
                          }                                               
                    } 
                   
                    fichier_chains(f_chaine, "fichier_chaines.txt");
                }
                
                
                
                public static double[] f_c(LinkedList<double[]> list_point,int index){
                    double nbr=0;
                        for (int i = index + 1 ; i < list_point.size(); i++) {
                        double[] p =(double[]) list_point.get(i);
                           if (p[0] != -1 && p[1] != -1) {
                              if (exist_neoud(p)) {
                               double[] F = new double[2];
                               F[0]=nbr;
                               F[1]=id_exist_neoud(p);
                               return F;
                                
                              }else{
                                  nbr++;
                              }
                            
                            }                                               
                        }
                   return null;                      
                    
                }
                
                
                
                public static boolean recherche2(double idDepart,double idArrrive,double nbr,LinkedList<double[]> f_chaine){
                       if(idDepart == idArrrive){
                           return true;
                       }else{
                            for (int i = 0 ; i < f_chaine.size(); i++) {
                        double[] C =(double[]) f_chaine.get(i);
                           if ((idDepart == C[2] && idArrrive == C[3] && nbr ==C[1])||
                               (idDepart == C[3] && idArrrive == C[2] && nbr ==C[1])    ) {
                              C[5]=1;
                              return true;
                            }                                               
                        }
                       }
                   return false;
                }
                
                
                
                
                
                
                
                
                
                public static int nbr_point(int depart, int arrive,LinkedList<double[]> list_point) {
                    int nbr = 0;
                    for (int i = depart + 1; i < arrive; i++) {
                       double[] p = list_point.get(i);
                        if (p[0] != -1 && p[1] != -1) {
                            nbr++;
                        }
                    }
                    return nbr;
                }
                
                

                
                
                
                public static void fichier_chains(
        LinkedList<double[]> f_chaine,
        String nomFichier) {

    BufferedWriter bw = null;

    try {
        bw = new BufferedWriter(new FileWriter(nomFichier));

        // en-tête (optionnel)
        bw.write("id, nbr_point, idDepart, idArrive ,zone_gauche,zone_droit");
        bw.newLine();

        for (double[] C : f_chaine) {

            bw.write(
                (int) C[0] + " ," +
                (int) C[1] + " ," +
                (int) C[2] + " ," +
                (int) C[3] + " ," +
                (int) C[4] + " ," +
                (int) C[5]
            );

            bw.newLine();
        }

    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            if (bw != null) bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    
                
                
}
	        







