public class Point {

    private double x;
    private double y;

    public Point(double x,double y){
        this.x=x;
        this.y=y;
    }
    @Override
    public int hashCode(){
        int h1=Double.hashCode(x);
        int h2=Double.hashCode(y);
        return ((h1<<8)^(h1<<16));
    }

    @Override 
    public boolean equals(Object o){
        if(!(o instanceof Point)){
            return false;
        }
        return ((Point) o).x == x &&((Point)o).y==y;    
        
    }
}
