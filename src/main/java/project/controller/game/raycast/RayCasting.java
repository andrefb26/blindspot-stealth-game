package project.controller.game.raycast;
import project.model.game.Position;
import project.model.game.arena.Arena;

public class RayCasting {
    Arena arena;
    public RayCasting(Arena arena) {
        this.arena=arena;
    }
    public boolean haslineofsight(Position a, Position b, int range) {
        //vetor x
        double vx= b.getX()-a.getX();
        double vy= b.getY()-a.getY();
        //queremos vetor unitario
        double modulo = Math.sqrt(vx*vx+vy*vy);
        if (modulo==0) return true;
        if (modulo>range) return false;
        vx= vx/modulo;
        vy=vy/modulo;
        double places= range*4;
        for (int i=1; i<places ; i++){
            Position tmp = new Position((int)Math.round(a.getX()+ i* 0.25*vx), (int) Math.round(a.getY()+ i*0.25* vy));
            if (isBlocking(tmp)){
                return false;
            }
            if (tmp.getX()==b.getX()&&tmp.getY()==b.getY()){
                return true;
            }
        }return false;


        }
        public boolean isBlocking(Position position){
            if (arena.isbox(position) ||arena.issafe(position)|| arena.iswall(position)){
                return true ;
            }
            return false;

        }


}
