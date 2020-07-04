package Field;

public class Field {
    private static char[][] gameField;
    private static int width;
    private static int height;
    private static int block;
    private static Field field;

    public static final char EMPTY_POSITION = '.';


    private Field(int width, int height){
        this.width = width;
        this.height = height;
        gameField = new char[height][width];
        block = (width + height) / 2;
    }

    //default 3x3
    public static void createField(){
        if(field == null){
            field = new Field(3,3);
        }
        setEmptyPositionAtField();
        paint();
    }

    public static void createField(int width, int height){
        if(field == null){
            field = new Field(height,width);
        }
        setEmptyPositionAtField();
        paint();
    }

    private static void setEmptyPositionAtField(){
            for(int i = 0; i < height; i++){
                for(int j = 0; j < width; j++){
                    gameField[i][j] = EMPTY_POSITION;
                }
            }
    }

    public static void paint(){
        if(field == null) createField();

        //paint
        for(int i = 0; i < width+1; i++)
            System.out.print(i + " ");
        System.out.println();
        for(int i = 0; i < height; i++){
            System.out.print(i+1 + " ");
            for(int j = 0; j < width; j++){
                System.out.print(gameField[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void setSimbleByPosition(int y, int x, char simble) throws IndexOutOfBoundsException, FieldIsFull {
        isFull();
        gameField[y][x] = simble;
    }

    private static void isFull() throws FieldIsFull{
        boolean full = true;

        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                if(gameField[i][j] == EMPTY_POSITION) full &= false;
            }
        }

        if(full) throw new FieldIsFull();
    }

    public static char[][] getGameField() {
        return gameField;
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static int getBlock() {
        return block;
    }
}
