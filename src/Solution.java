
// import java.util.*;

// import javafx.scene.Group;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// class Position{
// Position(){}
// int x ;
// int y ;
// public Position(int x, int y) {
// this.x = x;
// this.y = y;
// }
// void setX(int x){
// this.x = x;
// }

// void setY(int y){
// this.y = y;
// }

// int getX(){
// return x;
// }

// int getY(){
// return y;
// }

// public boolean equals(Object abc) {
// if (abc == this) {
// return true;
// }
// String abc_class = abc.getClass().toString();
// if (!(abc_class.contains("Position"))) {
// return false;
// }
// Position position = (Position) abc;
// return x == position.x && y == position.y;

// }

// public String toString(){
// return "(" + x + "," + y + ")";
// }
// }

// class Map extends App {

// Map(Scanner input){
// System.out.printf("");
// }
// public Map() {
// }
// private static int number;
// private static char[][] map;
// Position pos = new Position();

// public void map(int size) {
// Map.number = size;
// }
// void setPos(Position p){
// pos = p;
// }

// public void Map(Scanner input) throws InvalidMapException {

// setSize(input.nextInt());
// setArr(new char[number][number]);
// if(number == 0){throw new InvalidMapException("Map size can not be zero");}
// int num = 0;
// while(number > num){
// for(int p = 0; p <number;p++){
// String maps = input.next();
// if(maps.charAt(0)=='P'){
// pos.setY(num);
// pos.setX(p);
// }

// if(maps.charAt(0)=='P'||maps.charAt(0)=='0'||maps.charAt(0)=='1' ||
// maps.charAt(0)=='S') {
// char qew = maps.charAt(0);
// map[num][p] = qew;System.out.printf("");
// }
// else throw new InvalidMapException("Not enough map elements");
// }
// num++;
// }
// }
// void setSize(int r){
// number = r;
// }
// void setArr(char[][] mps){
// map = mps;
// }

// public int getSize() {
// return number;
// }

// public char getValueAt(int x, int y) {
// return map[x][y];
// }
// void print()
// {

// }

// }

// class InvalidMapException extends Exception{
// /**
// *
// */
// private static final long serialVersionUID = 1L;
// public InvalidMapException() {
// }
// public InvalidMapException(String message) {
// super(message);
// }

// }

// interface Player {
// void moveUp();
// void moveDown();
// void moveRight();
// void moveLeft();
// void setMap(Map map);
// Position getPosition();
// }

// class Game{
// Player pl = new MyPlayer();
// Map map = new Map();
// Game(Map x){
// map = x;
// }
// void setMap(Map x){
// map = x;
// }
// void addPlayer(Player x){
// pl = x;
// }
// }

// class MyPlayer implements Player {
// Position pos = new Position();
// private Map map = new Map();
// @Override
// public void moveLeft() {
// if (map.pos.getX() - 1 >= 0){
// if (map.getValueAt(pos.getY(), map.pos.getX() - 1 ) != '1') {
// map.pos.setX(pos.getX() - 1);
// }
// }
// }
// @Override
// public void moveRight() {
// if (map.pos.getX() + 1 < map.getSize()) {
// if (map.getValueAt(map.pos.getY(), map.pos.getX() + 1 ) != '1') {
// map.pos.setX(map.pos.getX() + 1);
// }
// }
// }
// public void moveUp() {
// if (0 <= pos.getY() - 1 && map.getValueAt(map.pos.getY() - 1, map.pos.getX())
// != '1')
// {
// pos.setY(map.pos.getY() - 1);

// }
// }
// @Override
// public void moveDown() {
// if (map.getSize() > pos.getY()+2-1 && map.getValueAt(pos.getY()+2-1,
// map.pos.getX()) != '1')
// {
// pos.setY(pos.getY()+2-1);
// }
// }
// public void setMap(Map map) {
// this.map = map;
// }
// public Position getPosition() {
// int x = map.pos.getX();
// int y = map.pos.getY();
// Position Position = new Position(x,y);

// return Position;

// }
// }

// public class Solution{

// public static void main(String[] args) throws InvalidMapException {
// Scanner input = new Scanner(System.in);
// String className = input.nextLine();

// // Checking the implementation of the Position class
// if(className.equals("Position")){
// Position p1 = new Position(input.nextInt(), input.nextInt());
// System.out.println(p1);
// p1.setX(5);
// System.out.println(p1.getX());

// Position p2 = new Position(5, 10);
// System.out.println(p1.equals(p2));
// }

// // Checking the implementation of the Map class
// else if(className.equals("Map")){
// try{
// Map map = new Map(input);
// map.print();
// int size = map.getSize();
// System.out.println(size);
// System.out.println(map.getValueAt(size/2, size/2));
// }
// catch(Exception e){}
// }

// // Checking the Player interface and the MyPlayer class
// else if(className.equals("Player")){
// Player player = new MyPlayer();
// System.out.println(Player.class.isInterface());
// System.out.println(player instanceof Player);
// System.out.println(player instanceof MyPlayer);
// }

// // Checking the InvalidMapException class
// else if(className.equals("Exception")){
// try{
// throw new InvalidMapException("Some message");
// }
// catch(InvalidMapException e){
// System.out.println(e.getMessage());
// }
// }

// // Checking the Game class and all of its components
// else if(className.equals("Game")){

// // Initialize player, map, and the game
// Player player = new MyPlayer();
// Game game = null;

// game = new Game(new Map(input));

// game.addPlayer(player);

// // Make the player move based on the commands given in the input
// String moves = input.next();
// char move;
// for(int i=0; i<moves.length(); i++){
// move = moves.charAt(i);
// switch(move){
// case 'R':
// player.moveRight();
// break;
// case 'L':
// player.moveLeft();
// break;
// case 'U':
// player.moveUp();
// break;
// case 'D':
// player.moveDown();
// break;
// }
// }

// // Determine the final position of the player after completing all the moves
// above
// Position playerPosition = player.getPosition();
// System.out.println("Player final position");
// System.out.println("Row: " + playerPosition.getY());
// System.out.println("Col: " + playerPosition.getX());
// }
// }
// }