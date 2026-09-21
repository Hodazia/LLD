import java.util.HashMap;

public class tut
{
    public static void main(String args[])
    {
        HashMap<Integer,Integer> map = new  HashMap<>();
    }
}

/*

default size of hashmap is 16, it is always in 2 raised to the power of x, x=4,5,6,....
so at first the size is 16, 
so when we do map.put(1,"hi"), we find hashcode(1) -> will get an index, suppose it is 13,
we store key, value, and next pointer,
So we store 1, 'hi', null,
then we do map.put(235,"zia"), suppose we get the hashcode same as 13, 
So we update the node (1,'hi',null) , the next will now point to the second node,
and we create a new node(235,'zia',null)

the size of inte data type is 2^31-1 to -2^31 , hashmap max size will always be 2^30


hashcode vs equals,
hashcode -> for the same object we will get the same value, different objects can also return the same value
equals -> if 2  objects are equal then their hashcode will be same, if not it does mean their hashcode cannot be same


load factor = .75,
if my hashmap capacity is 100, and if it si 75% full, then we have to resize, the next resize is gonna be in the power of 2,
from 2^6 -> 2^7

TreeFieThreshold -> 8, it will convert the linked list to trees, in order to optimize the searching for a key,
so it is O(logN) from O(N);

*/

/*

Design patterns,  is -a , has-a 

what if 2 child classes have same functionality/features with their own implementation,
then we use design patterns, 


observer pattern , implement notify me of amazon , whover has subscrubed to notify me, will be notoifed about an event happening,

Observable - if the state of observable changes, then all the observers of the observable will be notified about it, update it 

IT has 1-many relationships , 
List<ObserverInterf> objlist;

add(ObserverInterf obj) , // register a new observerinterf
{
OBJLIST.add(obj);
}
remove(ObserverInterf obj),  // remove observerinterf 
{
    objlist.remove(obj);
}
notify() , // notify all observers
{
// for(observerinterf obj:objList)
{
    obj.update()
}
}
setdata(int t)
{
data = t; // if anything is changed, then notify everyone,
notify();
}

observer - ??
constructor injection,
*/



