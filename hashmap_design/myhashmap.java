public class myhashmap {
    private static final int DEFAULT_SIZE =  1 << 4;
    private static final int MAX_SIZE = 1 << 30;
    public Entry[] hashTable;
    public myhashmap()
    {
        hashTable = new Entry[DEFAULT_SIZE];
    }
 
    // generic ?? 
    class Entry<K,V> {
        public K key;
        public V value;
        public Entry next;

        public Entry(K ky,V val)
        {
            key = ky;
            value = val; 
        }


        public void put(K key, V value)
        {
            int hashCode = key.hashCode() % hashTable.length;
            Entry node = hashTable[hashCode];

            if(node==null)
            {
                Entry newNode = new Entry(key,value);
                hashTable[hashCode] = newNode;
            }
            else{
                Entry previousNode = node;
                while(node!=null)
                {
                    if(node.key==key)
                    {
                        // if the key is same, just update the value
                        node.value = value;
                        return;
                    }
                    previousNode = node;
                    node = node.next;
                }
                Entry newNode = new Entry(key,value);
                previousNode.next = newNode;
            }
        }
    }
}

