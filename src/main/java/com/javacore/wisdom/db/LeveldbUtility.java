package com.javacore.wisdom.db;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Map;

import org.iq80.leveldb.*;
import org.iq80.leveldb.impl.Iq80DBFactory;

public class LeveldbUtility {
	private static final String PATH = "/data/leveldb";
	private static final Charset CHARSET = Charset.forName("utf-8");
	private static final File FILE = new File(PATH);

	//单个string数据插入
	public void writeOptionsString(String s1,String s2) {
		DBFactory factory = new Iq80DBFactory();
		Options options = new Options();
		DB db = null;
		try {
			db = factory.open(FILE, options);
			WriteOptions writeOptions = new WriteOptions().sync(true);	// 线程安全
			// 没有writeOptions时，会new一个，这里添加了这个参数的意义就是可以设置sync和snapshot参数，建议采用这种方式
			db.put(s1.getBytes(CHARSET), s2.getBytes(CHARSET));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (db != null) {
				try {
					db.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	//批量string数据插入
	public void writeOptionsMap(Map<String,String> maps) {
		DBFactory factory = new Iq80DBFactory();
		Options options = new Options();
		DB db = null;
		try {
			db = factory.open(FILE, options);
			WriteBatch writeBatch = db.createWriteBatch();
			for(Map.Entry<String, String> entry : maps.entrySet()){
				writeBatch.put(entry.getKey().getBytes(CHARSET), entry.getValue().getBytes(CHARSET));
			}
			db.write(writeBatch);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (db != null) {
				try {
					db.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	//单个删除
	public void deleteString(String key) {
		DBFactory factory = new Iq80DBFactory();
		Options options = new Options();
		DB db = null;
		try {
			db = factory.open(FILE, options);
			// 存在会删除，之后查询不出，说明可能不是真删除，而是添加一个标记，
			db.delete(key.getBytes(CHARSET));

			Snapshot snapshot = db.getSnapshot();
			ReadOptions readOptions = new ReadOptions();
			readOptions.fillCache(false);
			readOptions.snapshot(snapshot);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (db != null) {
				try {
					db.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	//批量删除
	public void deleteArray(String[] key) {
		DBFactory factory = new Iq80DBFactory();
		Options options = new Options();
		DB db = null;
		try {
			db = factory.open(FILE, options);
			for(int x=0;x<key.length;x++){
				db.delete(key[x].getBytes(CHARSET));
			}

			Snapshot snapshot = db.getSnapshot();
			ReadOptions readOptions = new ReadOptions();
			readOptions.fillCache(false);
			readOptions.snapshot(snapshot);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (db != null) {
				try {
					db.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	//返回String
	public String getString(String keys){
		DBFactory factory = new Iq80DBFactory();
		File file = new File(PATH);
		Options options = new Options();
		DB db = null;
		String values="";
		try {
			db = factory.open(file, options);
			// 读取当前快照，重启服务仍能读取，说明快照持久化至磁盘，
			Snapshot snapshot = db.getSnapshot();
			// 读取操作
			ReadOptions readOptions = new ReadOptions();
			// 遍历中swap出来的数据，不应该保存在memtable中。
			readOptions.fillCache(false);
			// 默认snapshot为当前
			readOptions.snapshot(snapshot);

			DBIterator it = db.iterator(readOptions);
			while (it.hasNext()) {
				Map.Entry<byte[], byte[]> entry = (Map.Entry<byte[], byte[]>) it
						.next();
				String key = new String(entry.getKey(), CHARSET);
				String value = new String(entry.getValue(), CHARSET);
				if (key.equals(keys)) {
					values=value;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (db != null) {
				try {
					db.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return values;
		}
	}

	//Obejct写入
	public void writeObject(String s,Object o) {
		Options options = new Options();
		DBFactory factory = Iq80DBFactory.factory;
		DB db = null;
		try {
			db = factory.open(FILE, options);
			WriteOptions writeOptions = new WriteOptions();
			writeOptions.snapshot(true);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(o);

			db.put(s.getBytes(CHARSET), baos.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (db != null) {
				try {
					db.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	//Object返回
	public Object readObject(String s) {
		Options options = new Options();
		DBFactory factory = Iq80DBFactory.factory;
		DB db = null;
		try {
			db = factory.open(FILE, options);
			byte[] valueByte = db.get(s.getBytes(CHARSET));
			ByteArrayInputStream bais = new ByteArrayInputStream(valueByte);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (Object) ois.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (db != null) {
				try {
					db.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}


	public static void main(String[] args){
/*        leveldbUtility l=new leveldbUtility();
        User u1=new User("恽",29);
        User u2=new User("张",26);
        List<User> ls=new ArrayList<>();
        ls.add(u1);
        ls.add(u2);
        l.writeObject("恽",ls);
        List<User> ll=(List<User>)l.readObject("恽");
        Map<String,Integer> m=new HashMap<>();
        m.put("恽",29);
        m.put("张",26);
        l.writeObject("恽",m);
        Map<String,Integer> ss=(Map<String,Integer>) l.readObject("恽");
        System.out.print(ss.size());*/
	}
}
