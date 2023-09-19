public class Array2D {

    private int[][] data;
    public Array2D(int height,int weight){

    }

    public void set(int h, int w,int value){
        data[h][w]=value;
    }

    public int get(int h,int w){
        return data[h][w];
    }
    
    @Override
    public int hashCode(){
        int h=0;
        for(int i=0;i<data.length;i++){
            for(int j=0;j<data[i].length;j++){
                h ^=((h<<8)^data[i][j])+h;
            }
        }
        return h;
    }
}
