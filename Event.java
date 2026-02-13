public class Event {
    public EventType type=null;
    public long time;
    public Event(EventType type, long time)
    {
        this.type=type;
        this.time=time;
    }
    public EventType getType() {
        return type;
    }
    public long getTime() {
        return time;
    }
    
}
