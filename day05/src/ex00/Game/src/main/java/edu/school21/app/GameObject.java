package edu.school21.app;

public record GameObject(edu.school21.app.GameObject.ObjectType type) {

    public enum MapSymbol{
        ENEMY('e'),
        PLAYER('p'),
        WALL('w'),
        GOAL('g'),
        EMPTY(' ');
        private char symbol;

        MapSymbol(char symbol){
            this.symbol = symbol;
        }

        public char getSymbol(){
            return symbol;
        }

        private void setSymbol(char symbol){
            this.symbol = symbol;
        }
    }

    public enum MapColor{
         RED, BLUE, YELLOW, MAGENTA, BLACK, GREEN, CYAN, WHITE;

         public static MapColor fromStringToEnum (String textColor){
             for (MapColor color : MapColor.values()) {
                 if (color.toString().equalsIgnoreCase(textColor)) {
                     return color;
                 }
             }
             return null;
         }
    }

    public enum ObjectType {
        ENEMY(MapSymbol.ENEMY, MapColor.RED),
        PLAYER(MapSymbol.PLAYER, MapColor.GREEN),
        WALL(MapSymbol.WALL, MapColor.BLUE),
        GOAL(MapSymbol.GOAL, MapColor.CYAN),
        EMPTY(MapSymbol.EMPTY, MapColor.WHITE);
        private final MapSymbol symbol;
        private MapColor color;

        ObjectType(MapSymbol symbol, MapColor color) {
            this.symbol = symbol;
            this.color = color;
        }

        public char getSymbol() {
            return symbol.getSymbol();
        }

        public void setSymbol(char symbol) {
            this.symbol.setSymbol(symbol);
        }

        public void setColor(MapColor color) {
            this.color = color;
        }

        public MapColor getColor() {
            return color;
        }
    }
}
