package com.javacore.wisdom.db;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBFactory;
import org.iq80.leveldb.Options;
import org.iq80.leveldb.ReadOptions;
import org.iq80.leveldb.Snapshot;
import org.iq80.leveldb.WriteBatch;
import org.iq80.leveldb.WriteOptions;
import org.iq80.leveldb.impl.Iq80DBFactory;

public class LeveldbUtility {
	private static final String PATH = "/data/leveldb";
	private static final Charset CHARSET = Charset.forName("utf-8");
	private static final File FILE = new File(PATH);

	// single data process
	public void writeOptionsString(String keyData, String valueData) {
		DBFactory factory = new Iq80DBFactory();
		Options options = new Options();
		DB db = null;
		try {
			db = factory.open(FILE, options);
			// Thread safe
			WriteOptions writeOptions = new WriteOptions().sync(true);
			db.put(keyData.getBytes(CHARSET), valueData.getBytes(CHARSET), writeOptions);
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

	// batch data process
	public void writeOptionsMap(Map<String, String> maps) {
		DBFactory factory = new Iq80DBFactory();
		Options options = new Options();
		DB db = null;
		try {
			db = factory.open(FILE, options);
			WriteBatch writeBatch = db.createWriteBatch();
			for (Map.Entry<String, String> entry : maps.entrySet()) {
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

	// single delete
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

	// batch delete
	public void deleteArray(String[] key) {
		DBFactory factory = new Iq80DBFactory();
		Options options = new Options();
		DB db = null;
		try {
			db = factory.open(FILE, options);
			for (int x = 0; x < key.length; x++) {
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

	
	
	
	
	
}
