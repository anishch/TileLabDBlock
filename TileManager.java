//Anish Chaudhuri - D Block (Post-AP Data Structures)
// This file is the TileManager class/object of
//the overall project, which is called by the TileMain
//class and is for the purpose of ArrayList management
//which is then used by other classes to display which 
//tiles should take precedence or be on top for z-ordering


import java.util.*;
import java.awt.*;

public class TileManager{

   private ArrayList<Tile> list; //Declaring ArrayList of Tiles
   
   //Below is the constructor, 
   // which will create a new ArrayList of Tiles
   // that the program will keep track of, without any parameters in order to 
   //create the TileManager() object.
   public TileManager(){
      list = new ArrayList<Tile>(); //creating new ArrayList
   }
   
   // Below is the addTile method, 
   // which will add to the ArrayList of Tiles
   // that the program will keep track of, with the parameter of a Tile
   // without returning anything.
   public void addTile(Tile rect){
      list.add(rect); //adding rectangle to end of list
   }
   
   // Below is the drawAll method, 
   // which will traverse the ArrayList
   // and make sure to draw each tile (using methods from the Tile class), 
   // with the parameter of some Graphics
   // without returning anything.
   public void drawAll(Graphics g){
      for (Tile tiles : list){
         tiles.draw(g); //draw each tile
      }
   }
   
   // Below is the raise method, 
   // which will find the top-most of the tiles
   // and make sure to move it to the end of the list 
   // with the parameter of the position of the mouse click
   // without returning anything.
   public void raise(int x, int y){
      System.out.println(x); //for debugging purposes
      System.out.println(y); //for debugging purposes
      int index = index(list, x, y); //set index
      Tile tile = list.get(index); //create a placeholder for the tile
      list.remove(index); //delete the index in the list
      list.add(tile); //add the tile to the end of the list
   }
   
   // Below is the lower method, 
   // which will find the top-most of the tiles
   // and make sure to move it to the front of the list 
   // with the parameter of the position of the mouse click
   // without returning anything.
   public void lower(int x, int y){
      int index = index(list, x, y); //set index
      Tile tile = list.get(index); //create a placeholder for the tile
      list.remove(index); //delete the index in the list
      list.add(0, tile); //add the tile to the front of the list
   }
   
   // Below is the deleteAll method, 
   // which will delete all tiles that are
   // possibly being touched by the mouse
   // with the parameter of the position of the mouse click
   // without returning anything.
   public void deleteAll(int x, int y){
      ArrayList<Integer> toBeDeleted = new ArrayList<Integer>();
      if (list.size() != 0){
         for (Tile tiles : list){ //for each tile
            for (int i = 0; i <= tiles.getWidth(); i++){ //all possible increments or positions to the right of getX()
               if (tiles.getX() + i == x){ //if we reach that point
                  for (int j = 0; j <= tiles.getHeight(); j++){ //then check the ys
                     if (tiles.getY() + j == y){ //if we reach y point
                        toBeDeleted.add(list.indexOf(tiles));
                     }
                  }      
               }
            }
         }
         int i = 0;
         for (int ints : toBeDeleted){
            list.remove(ints - i); //remove tile located "there"
            i++; //increment thingtobesubtracted, as we are going from left to right
         }
      }
   }
   
   // Below is the deleteAll method, 
   // which will delete the top-most tile that is
   // possibly being touched by the mouse
   // with the parameter of the position of the mouse click
   // without returning anything.
   public void delete(int x, int y){ //delete method
      int index = index(list, x, y); //find your index
      list.remove(index); //delete the Tile in that index
   }
   
   // Below is the shuffle method, 
   // which will shuffle the order of the elements of the list
   // and set positions of the tiles dependent on the
   // parameters given on width and height
   // without returning anything.
   public void shuffle(int width, int height){ //params
      Collections.shuffle(list); //shuffle list
      for (Tile tiles : list){
         tiles.setY((int)(Math.random() * (height - tiles.getHeight()))); //set random height
         tiles.setX((int)(Math.random() * (width - tiles.getWidth())));  //set random x
      }
   }
   
   // Below is the index method, 
   // which will find the index of the top-most tile
   // based on the 
   // parameters of the overall list, and x-y position of the mouse click,
   // and return that index as an integer.
   public int index(ArrayList<Tile> list, int a, int b){ //shortened method to find top of stack
      System.out.println(a); //for debugging purposes
      System.out.println(b); //for debugging purposes
      ArrayList<Integer> indices = new ArrayList<Integer>(); 
      for (Tile tiles : list){ //for all tiles in the list
         for (int i = 0; i <= tiles.getWidth(); i++){ //increment from zero to getWidth
            if (tiles.getX() + i == a){ //if tiles spans these x coordinates
               for (int j = 0; j <= tiles.getHeight(); j++){ //increment from zero to height
                  if (tiles.getY() + j == b){ //top - height
                     indices.add(list.indexOf(tiles)); //add index positions of qualifying tiles to the list
                     System.out.println(tiles.toString()); //for debugging purposes
                  }
               }      
            }
         }
      }
      //System.out.println(indices.toString());
      return indices.get(indices.size() - 1); //return statement
   }
}



