package Day_12;

public class Moon {
    public int xPosition;
    public int yPosition;
    public int zPosition;
    public int xVelocity;
    public int yVelocity;
    public int zVelocity;

    public int getPotentialEnergy(){
        return (Math.abs(this.xPosition) + Math.abs(this.yPosition) + Math.abs(this.zPosition));
    }

    public int getKineticEnergy(){
        return (Math.abs(this.xVelocity) + Math.abs(this.yVelocity) + Math.abs(this.zVelocity));
    }

    public int getTotalEnergy(){
        return this.getPotentialEnergy() * this.getKineticEnergy();
    }

    public Moon(int xPosition, int yPosition, int zPosition){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.zPosition = zPosition;
        this.xVelocity = 0;
        this.yVelocity = 0;
    }

    public String toString(){
        return "pos=<x=" + this.xPosition + ", y=" + this.yPosition + ", z=" + this.zPosition + ">, vel=<x=" + xVelocity + ", y=" + yVelocity + ", z=" + zVelocity + ">";
    }

}
