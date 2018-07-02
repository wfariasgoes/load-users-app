package br.com.sankhya.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import br.com.sankhya.model.UserItem;
import br.com.sankhya.network.response.Datum;


public class DataBaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "sankhya.sqlite";
	private static final int DATABASE_VERSION = 1;

	public DataBaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, UserItem.class);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void populateInitialData() throws SQLException {
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
		try {
			TableUtils.dropTable(connectionSource, UserItem.class, true);
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void close() {
		super.close();
	}
	
}

