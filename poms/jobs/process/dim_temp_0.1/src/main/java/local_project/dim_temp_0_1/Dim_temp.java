// ============================================================================
//
// Copyright (c) 2006-2015, Talend SA
//
// Ce code source a été automatiquement généré par_Talend Open Studio for Data Integration
// / Soumis à la Licence Apache, Version 2.0 (la "Licence") ;
// votre utilisation de ce fichier doit respecter les termes de la Licence.
// Vous pouvez obtenir une copie de la Licence sur
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Sauf lorsqu'explicitement prévu par la loi en vigueur ou accepté par écrit, le logiciel
// distribué sous la Licence est distribué "TEL QUEL",
// SANS GARANTIE OU CONDITION D'AUCUNE SORTE, expresse ou implicite.
// Consultez la Licence pour connaître la terminologie spécifique régissant les autorisations et
// les limites prévues par la Licence.

package local_project.dim_temp_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")

/**
 * Job: Dim_temp Purpose: <br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class Dim_temp implements TalendJob {

	protected static void logIgnoredError(String message, Throwable cause) {
		System.err.println(message);
		if (cause != null) {
			cause.printStackTrace();
		}

	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "Dim_temp";
	private final String projectName = "LOCAL_PROJECT";
	public Integer errorCode = null;
	private String currentComponent = "";

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private RunStat runStat = new RunStat();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	public void setDataSourceReferences(List serviceReferences) throws Exception {

		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();

		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils
				.getServices(serviceReferences, javax.sql.DataSource.class).entrySet()) {
			dataSources.put(entry.getKey(), entry.getValue());
			talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;
		private String currentComponent = null;
		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					Dim_temp.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(Dim_temp.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tDBInput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_6_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_5_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_4_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_4_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_4_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_4_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_5_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_5_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_5_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_5_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_5_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tMap_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tDBInput_2_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tDBInput_3_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tDBInput_4_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tDBInput_5_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tMap_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class TeempStruct implements routines.system.IPersistableRow<TeempStruct> {
		final static byte[] commonByteArrayLock_LOCAL_PROJECT_Dim_temp = new byte[0];
		static byte[] commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int ID_Temp;

		public int getID_Temp() {
			return this.ID_Temp;
		}

		public java.util.Date Annee;

		public java.util.Date getAnnee() {
			return this.Annee;
		}

		public java.util.Date Mois;

		public java.util.Date getMois() {
			return this.Mois;
		}

		public java.util.Date Jour;

		public java.util.Date getJour() {
			return this.Jour;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + (int) this.ID_Temp;

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final TeempStruct other = (TeempStruct) obj;

			if (this.ID_Temp != other.ID_Temp)
				return false;

			return true;
		}

		public void copyDataTo(TeempStruct other) {

			other.ID_Temp = this.ID_Temp;
			other.Annee = this.Annee;
			other.Mois = this.Mois;
			other.Jour = this.Jour;

		}

		public void copyKeysDataTo(TeempStruct other) {

			other.ID_Temp = this.ID_Temp;

		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_LOCAL_PROJECT_Dim_temp) {

				try {

					int length = 0;

					this.ID_Temp = dis.readInt();

					this.Annee = readDate(dis);

					this.Mois = readDate(dis);

					this.Jour = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_LOCAL_PROJECT_Dim_temp) {

				try {

					int length = 0;

					this.ID_Temp = dis.readInt();

					this.Annee = readDate(dis);

					this.Mois = readDate(dis);

					this.Jour = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.ID_Temp);

				// java.util.Date

				writeDate(this.Annee, dos);

				// java.util.Date

				writeDate(this.Mois, dos);

				// java.util.Date

				writeDate(this.Jour, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.ID_Temp);

				// java.util.Date

				writeDate(this.Annee, dos);

				// java.util.Date

				writeDate(this.Mois, dos);

				// java.util.Date

				writeDate(this.Jour, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID_Temp=" + String.valueOf(ID_Temp));
			sb.append(",Annee=" + String.valueOf(Annee));
			sb.append(",Mois=" + String.valueOf(Mois));
			sb.append(",Jour=" + String.valueOf(Jour));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(TeempStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ID_Temp, other.ID_Temp);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row5Struct implements routines.system.IPersistableRow<row5Struct> {
		final static byte[] commonByteArrayLock_LOCAL_PROJECT_Dim_temp = new byte[0];
		static byte[] commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[0];

		public int ID_Temp;

		public int getID_Temp() {
			return this.ID_Temp;
		}

		public String Date_vente;

		public String getDate_vente() {
			return this.Date_vente;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_LOCAL_PROJECT_Dim_temp.length) {
					if (length < 1024 && commonByteArray_LOCAL_PROJECT_Dim_temp.length == 0) {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[1024];
					} else {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length);
				strReturn = new String(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_LOCAL_PROJECT_Dim_temp.length) {
					if (length < 1024 && commonByteArray_LOCAL_PROJECT_Dim_temp.length == 0) {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[1024];
					} else {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length);
				strReturn = new String(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_LOCAL_PROJECT_Dim_temp) {

				try {

					int length = 0;

					this.ID_Temp = dis.readInt();

					this.Date_vente = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_LOCAL_PROJECT_Dim_temp) {

				try {

					int length = 0;

					this.ID_Temp = dis.readInt();

					this.Date_vente = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.ID_Temp);

				// String

				writeString(this.Date_vente, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.ID_Temp);

				// String

				writeString(this.Date_vente, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID_Temp=" + String.valueOf(ID_Temp));
			sb.append(",Date_vente=" + Date_vente);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row5Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row5Struct row5 = new row5Struct();
				TeempStruct Teemp = new TeempStruct();

				/**
				 * [tDBOutput_5 begin ] start
				 */

				ok_Hash.put("tDBOutput_5", false);
				start_Hash.put("tDBOutput_5", System.currentTimeMillis());

				currentComponent = "tDBOutput_5";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "Teemp");
				}

				int tos_count_tDBOutput_5 = 0;

				int nb_line_tDBOutput_5 = 0;
				int nb_line_update_tDBOutput_5 = 0;
				int nb_line_inserted_tDBOutput_5 = 0;
				int nb_line_deleted_tDBOutput_5 = 0;
				int nb_line_rejected_tDBOutput_5 = 0;

				int deletedCount_tDBOutput_5 = 0;
				int updatedCount_tDBOutput_5 = 0;
				int insertedCount_tDBOutput_5 = 0;
				int rowsToCommitCount_tDBOutput_5 = 0;
				int rejectedCount_tDBOutput_5 = 0;

				String tableName_tDBOutput_5 = "Dim_temp";
				boolean whetherReject_tDBOutput_5 = false;

				java.util.Calendar calendar_tDBOutput_5 = java.util.Calendar.getInstance();
				calendar_tDBOutput_5.set(1, 0, 1, 0, 0, 0);
				long year1_tDBOutput_5 = calendar_tDBOutput_5.getTime().getTime();
				calendar_tDBOutput_5.set(10000, 0, 1, 0, 0, 0);
				long year10000_tDBOutput_5 = calendar_tDBOutput_5.getTime().getTime();
				long date_tDBOutput_5;

				java.sql.Connection conn_tDBOutput_5 = null;

				String properties_tDBOutput_5 = "noDatetimeStringSync=true";
				if (properties_tDBOutput_5 == null || properties_tDBOutput_5.trim().length() == 0) {
					properties_tDBOutput_5 = "rewriteBatchedStatements=true&allowLoadLocalInfile=true";
				} else {
					if (!properties_tDBOutput_5.contains("rewriteBatchedStatements=")) {
						properties_tDBOutput_5 += "&rewriteBatchedStatements=true";
					}

					if (!properties_tDBOutput_5.contains("allowLoadLocalInfile=")) {
						properties_tDBOutput_5 += "&allowLoadLocalInfile=true";
					}
				}

				String url_tDBOutput_5 = "jdbc:mysql://" + "127.0.0.1" + ":" + "3306" + "/" + "Cube_olap" + "?"
						+ properties_tDBOutput_5;

				String driverClass_tDBOutput_5 = "com.mysql.jdbc.Driver";

				String dbUser_tDBOutput_5 = "root";

				final String decryptedPassword_tDBOutput_5 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:Yk/38N2Z+4Ag+fTiTw5Brzsg+nYYdp1dXctoww8nKn/4AqRvnz8eCi9uwoM=");

				String dbPwd_tDBOutput_5 = decryptedPassword_tDBOutput_5;
				java.lang.Class.forName(driverClass_tDBOutput_5);

				conn_tDBOutput_5 = java.sql.DriverManager.getConnection(url_tDBOutput_5, dbUser_tDBOutput_5,
						dbPwd_tDBOutput_5);

				resourceMap.put("conn_tDBOutput_5", conn_tDBOutput_5);
				conn_tDBOutput_5.setAutoCommit(false);
				int commitEvery_tDBOutput_5 = 10000;
				int commitCounter_tDBOutput_5 = 0;

				int count_tDBOutput_5 = 0;

				java.sql.DatabaseMetaData dbMetaData_tDBOutput_5 = conn_tDBOutput_5.getMetaData();
				java.sql.ResultSet rsTable_tDBOutput_5 = dbMetaData_tDBOutput_5.getTables("Cube_olap", null, null,
						new String[] { "TABLE" });
				boolean whetherExist_tDBOutput_5 = false;
				while (rsTable_tDBOutput_5.next()) {
					String table_tDBOutput_5 = rsTable_tDBOutput_5.getString("TABLE_NAME");
					if (table_tDBOutput_5.equalsIgnoreCase("Dim_temp")) {
						whetherExist_tDBOutput_5 = true;
						break;
					}
				}
				if (whetherExist_tDBOutput_5) {
					try (java.sql.Statement stmtDrop_tDBOutput_5 = conn_tDBOutput_5.createStatement()) {
						stmtDrop_tDBOutput_5.execute("DROP TABLE `" + tableName_tDBOutput_5 + "`");
					}
				}
				try (java.sql.Statement stmtCreate_tDBOutput_5 = conn_tDBOutput_5.createStatement()) {
					stmtCreate_tDBOutput_5.execute("CREATE TABLE `" + tableName_tDBOutput_5
							+ "`(`ID_Temp` INT(10)   not null ,`Annee` DATETIME ,`Mois` DATETIME ,`Jour` DATETIME ,primary key(`ID_Temp`))");
				}

				String insert_tDBOutput_5 = "INSERT INTO `" + "Dim_temp"
						+ "` (`ID_Temp`,`Annee`,`Mois`,`Jour`) VALUES (?,?,?,?)";
				int batchSize_tDBOutput_5 = 100;
				int batchSizeCounter_tDBOutput_5 = 0;

				java.sql.PreparedStatement pstmt_tDBOutput_5 = conn_tDBOutput_5.prepareStatement(insert_tDBOutput_5);
				resourceMap.put("pstmt_tDBOutput_5", pstmt_tDBOutput_5);

				/**
				 * [tDBOutput_5 begin ] stop
				 */

				/**
				 * [tMap_6 begin ] start
				 */

				ok_Hash.put("tMap_6", false);
				start_Hash.put("tMap_6", System.currentTimeMillis());

				currentComponent = "tMap_6";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row5");
				}

				int tos_count_tMap_6 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_6__Struct {
				}
				Var__tMap_6__Struct Var__tMap_6 = new Var__tMap_6__Struct();
// ###############################

// ###############################
// # Outputs initialization
				TeempStruct Teemp_tmp = new TeempStruct();
// ###############################

				/**
				 * [tMap_6 begin ] stop
				 */

				/**
				 * [tDBInput_1 begin ] start
				 */

				ok_Hash.put("tDBInput_1", false);
				start_Hash.put("tDBInput_1", System.currentTimeMillis());

				currentComponent = "tDBInput_1";

				int tos_count_tDBInput_1 = 0;

				java.util.Calendar calendar_tDBInput_1 = java.util.Calendar.getInstance();
				calendar_tDBInput_1.set(0, 0, 0, 0, 0, 0);
				java.util.Date year0_tDBInput_1 = calendar_tDBInput_1.getTime();
				int nb_line_tDBInput_1 = 0;
				java.sql.Connection conn_tDBInput_1 = null;
				String driverClass_tDBInput_1 = "com.mysql.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_1 = java.lang.Class.forName(driverClass_tDBInput_1);
				String dbUser_tDBInput_1 = "root";

				final String decryptedPassword_tDBInput_1 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:gru3E8q2iokBzAVynvN7tX6GL7E9XztfuQzzV2SM4MEK1/3Q6BBYUwpcouA=");

				String dbPwd_tDBInput_1 = decryptedPassword_tDBInput_1;

				String properties_tDBInput_1 = "noDatetimeStringSync=true";
				if (properties_tDBInput_1 == null || properties_tDBInput_1.trim().length() == 0) {
					properties_tDBInput_1 = "";
				}
				String url_tDBInput_1 = "jdbc:mysql://" + "127.0.0.1" + ":" + "3306" + "/" + "data_warehouse_vente"
						+ "?" + properties_tDBInput_1;

				conn_tDBInput_1 = java.sql.DriverManager.getConnection(url_tDBInput_1, dbUser_tDBInput_1,
						dbPwd_tDBInput_1);

				java.sql.Statement stmt_tDBInput_1 = conn_tDBInput_1.createStatement();

				String dbquery_tDBInput_1 = "SELECT \n  `temp`.`ID_Temp`, \n  `temp`.`Date_vente`\nFROM `temp`";

				globalMap.put("tDBInput_1_QUERY", dbquery_tDBInput_1);
				java.sql.ResultSet rs_tDBInput_1 = null;

				try {
					rs_tDBInput_1 = stmt_tDBInput_1.executeQuery(dbquery_tDBInput_1);
					java.sql.ResultSetMetaData rsmd_tDBInput_1 = rs_tDBInput_1.getMetaData();
					int colQtyInRs_tDBInput_1 = rsmd_tDBInput_1.getColumnCount();

					String tmpContent_tDBInput_1 = null;

					while (rs_tDBInput_1.next()) {
						nb_line_tDBInput_1++;

						if (colQtyInRs_tDBInput_1 < 1) {
							row5.ID_Temp = 0;
						} else {

							row5.ID_Temp = rs_tDBInput_1.getInt(1);
							if (rs_tDBInput_1.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_1 < 2) {
							row5.Date_vente = null;
						} else {

							row5.Date_vente = routines.system.JDBCUtil.getString(rs_tDBInput_1, 2, false);
						}

						/**
						 * [tDBInput_1 begin ] stop
						 */

						/**
						 * [tDBInput_1 main ] start
						 */

						currentComponent = "tDBInput_1";

						tos_count_tDBInput_1++;

						/**
						 * [tDBInput_1 main ] stop
						 */

						/**
						 * [tDBInput_1 process_data_begin ] start
						 */

						currentComponent = "tDBInput_1";

						/**
						 * [tDBInput_1 process_data_begin ] stop
						 */

						/**
						 * [tMap_6 main ] start
						 */

						currentComponent = "tMap_6";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row5"

							);
						}

						boolean hasCasePrimitiveKeyWithNull_tMap_6 = false;

						// ###############################
						// # Input tables (lookups)
						boolean rejectedInnerJoin_tMap_6 = false;
						boolean mainRowRejected_tMap_6 = false;

						// ###############################
						{ // start of Var scope

							// ###############################
							// # Vars tables

							Var__tMap_6__Struct Var = Var__tMap_6;// ###############################
							// ###############################
							// # Output tables

							Teemp = null;

// # Output table : 'Teemp'
							Teemp_tmp.ID_Temp = row5.ID_Temp;
							Teemp_tmp.Annee = TalendDate.parseDate("yyyy", row5.Date_vente);
							Teemp_tmp.Mois = TalendDate.parseDate("MM", row5.Date_vente);
							Teemp_tmp.Jour = TalendDate.parseDate("dd", row5.Date_vente);
							Teemp = Teemp_tmp;
// ###############################

						} // end of Var scope

						rejectedInnerJoin_tMap_6 = false;

						tos_count_tMap_6++;

						/**
						 * [tMap_6 main ] stop
						 */

						/**
						 * [tMap_6 process_data_begin ] start
						 */

						currentComponent = "tMap_6";

						/**
						 * [tMap_6 process_data_begin ] stop
						 */
// Start of branch "Teemp"
						if (Teemp != null) {

							/**
							 * [tDBOutput_5 main ] start
							 */

							currentComponent = "tDBOutput_5";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "Teemp"

								);
							}

							whetherReject_tDBOutput_5 = false;
							pstmt_tDBOutput_5.setInt(1, Teemp.ID_Temp);

							if (Teemp.Annee != null) {
								date_tDBOutput_5 = Teemp.Annee.getTime();
								if (date_tDBOutput_5 < year1_tDBOutput_5 || date_tDBOutput_5 >= year10000_tDBOutput_5) {
									pstmt_tDBOutput_5.setString(2, "0000-00-00 00:00:00");
								} else {
									pstmt_tDBOutput_5.setTimestamp(2, new java.sql.Timestamp(date_tDBOutput_5));
								}
							} else {
								pstmt_tDBOutput_5.setNull(2, java.sql.Types.DATE);
							}

							if (Teemp.Mois != null) {
								date_tDBOutput_5 = Teemp.Mois.getTime();
								if (date_tDBOutput_5 < year1_tDBOutput_5 || date_tDBOutput_5 >= year10000_tDBOutput_5) {
									pstmt_tDBOutput_5.setString(3, "0000-00-00 00:00:00");
								} else {
									pstmt_tDBOutput_5.setTimestamp(3, new java.sql.Timestamp(date_tDBOutput_5));
								}
							} else {
								pstmt_tDBOutput_5.setNull(3, java.sql.Types.DATE);
							}

							if (Teemp.Jour != null) {
								date_tDBOutput_5 = Teemp.Jour.getTime();
								if (date_tDBOutput_5 < year1_tDBOutput_5 || date_tDBOutput_5 >= year10000_tDBOutput_5) {
									pstmt_tDBOutput_5.setString(4, "0000-00-00 00:00:00");
								} else {
									pstmt_tDBOutput_5.setTimestamp(4, new java.sql.Timestamp(date_tDBOutput_5));
								}
							} else {
								pstmt_tDBOutput_5.setNull(4, java.sql.Types.DATE);
							}

							pstmt_tDBOutput_5.addBatch();
							nb_line_tDBOutput_5++;

							batchSizeCounter_tDBOutput_5++;
							if (batchSize_tDBOutput_5 <= batchSizeCounter_tDBOutput_5) {
								try {
									int countSum_tDBOutput_5 = 0;
									for (int countEach_tDBOutput_5 : pstmt_tDBOutput_5.executeBatch()) {
										countSum_tDBOutput_5 += (countEach_tDBOutput_5 == java.sql.Statement.EXECUTE_FAILED
												? 0
												: 1);
									}
									rowsToCommitCount_tDBOutput_5 += countSum_tDBOutput_5;
									insertedCount_tDBOutput_5 += countSum_tDBOutput_5;
								} catch (java.sql.BatchUpdateException e) {
									globalMap.put("tDBOutput_5_ERROR_MESSAGE", e.getMessage());
									int countSum_tDBOutput_5 = 0;
									for (int countEach_tDBOutput_5 : e.getUpdateCounts()) {
										countSum_tDBOutput_5 += (countEach_tDBOutput_5 < 0 ? 0 : countEach_tDBOutput_5);
									}
									rowsToCommitCount_tDBOutput_5 += countSum_tDBOutput_5;
									insertedCount_tDBOutput_5 += countSum_tDBOutput_5;
									System.err.println(e.getMessage());
								}

								batchSizeCounter_tDBOutput_5 = 0;
							}
							commitCounter_tDBOutput_5++;

							if (commitEvery_tDBOutput_5 <= commitCounter_tDBOutput_5) {

								try {
									int countSum_tDBOutput_5 = 0;
									for (int countEach_tDBOutput_5 : pstmt_tDBOutput_5.executeBatch()) {
										countSum_tDBOutput_5 += (countEach_tDBOutput_5 < 0 ? 0 : 1);
									}
									rowsToCommitCount_tDBOutput_5 += countSum_tDBOutput_5;
									insertedCount_tDBOutput_5 += countSum_tDBOutput_5;
								} catch (java.sql.BatchUpdateException e) {
									globalMap.put("tDBOutput_5_ERROR_MESSAGE", e.getMessage());
									int countSum_tDBOutput_5 = 0;
									for (int countEach_tDBOutput_5 : e.getUpdateCounts()) {
										countSum_tDBOutput_5 += (countEach_tDBOutput_5 < 0 ? 0 : countEach_tDBOutput_5);
									}
									rowsToCommitCount_tDBOutput_5 += countSum_tDBOutput_5;
									insertedCount_tDBOutput_5 += countSum_tDBOutput_5;
									System.err.println(e.getMessage());

								}
								if (rowsToCommitCount_tDBOutput_5 != 0) {
								}
								conn_tDBOutput_5.commit();
								if (rowsToCommitCount_tDBOutput_5 != 0) {
									rowsToCommitCount_tDBOutput_5 = 0;
								}
								commitCounter_tDBOutput_5 = 0;

							}

							tos_count_tDBOutput_5++;

							/**
							 * [tDBOutput_5 main ] stop
							 */

							/**
							 * [tDBOutput_5 process_data_begin ] start
							 */

							currentComponent = "tDBOutput_5";

							/**
							 * [tDBOutput_5 process_data_begin ] stop
							 */

							/**
							 * [tDBOutput_5 process_data_end ] start
							 */

							currentComponent = "tDBOutput_5";

							/**
							 * [tDBOutput_5 process_data_end ] stop
							 */

						} // End of branch "Teemp"

						/**
						 * [tMap_6 process_data_end ] start
						 */

						currentComponent = "tMap_6";

						/**
						 * [tMap_6 process_data_end ] stop
						 */

						/**
						 * [tDBInput_1 process_data_end ] start
						 */

						currentComponent = "tDBInput_1";

						/**
						 * [tDBInput_1 process_data_end ] stop
						 */

						/**
						 * [tDBInput_1 end ] start
						 */

						currentComponent = "tDBInput_1";

					}
				} finally {
					if (rs_tDBInput_1 != null) {
						rs_tDBInput_1.close();
					}
					if (stmt_tDBInput_1 != null) {
						stmt_tDBInput_1.close();
					}
					if (conn_tDBInput_1 != null && !conn_tDBInput_1.isClosed()) {

						conn_tDBInput_1.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}

				}

				globalMap.put("tDBInput_1_NB_LINE", nb_line_tDBInput_1);

				ok_Hash.put("tDBInput_1", true);
				end_Hash.put("tDBInput_1", System.currentTimeMillis());

				/**
				 * [tDBInput_1 end ] stop
				 */

				/**
				 * [tMap_6 end ] start
				 */

				currentComponent = "tMap_6";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row5");
				}

				ok_Hash.put("tMap_6", true);
				end_Hash.put("tMap_6", System.currentTimeMillis());

				/**
				 * [tMap_6 end ] stop
				 */

				/**
				 * [tDBOutput_5 end ] start
				 */

				currentComponent = "tDBOutput_5";

				try {
					if (batchSizeCounter_tDBOutput_5 != 0) {
						int countSum_tDBOutput_5 = 0;

						for (int countEach_tDBOutput_5 : pstmt_tDBOutput_5.executeBatch()) {
							countSum_tDBOutput_5 += (countEach_tDBOutput_5 == java.sql.Statement.EXECUTE_FAILED ? 0
									: 1);
						}
						rowsToCommitCount_tDBOutput_5 += countSum_tDBOutput_5;

						insertedCount_tDBOutput_5 += countSum_tDBOutput_5;

					}

				} catch (java.sql.BatchUpdateException e) {
					globalMap.put(currentComponent + "_ERROR_MESSAGE", e.getMessage());

					int countSum_tDBOutput_5 = 0;
					for (int countEach_tDBOutput_5 : e.getUpdateCounts()) {
						countSum_tDBOutput_5 += (countEach_tDBOutput_5 < 0 ? 0 : countEach_tDBOutput_5);
					}
					rowsToCommitCount_tDBOutput_5 += countSum_tDBOutput_5;

					insertedCount_tDBOutput_5 += countSum_tDBOutput_5;

					System.err.println(e.getMessage());

				}
				batchSizeCounter_tDBOutput_5 = 0;

				if (pstmt_tDBOutput_5 != null) {

					pstmt_tDBOutput_5.close();
					resourceMap.remove("pstmt_tDBOutput_5");

				}
				resourceMap.put("statementClosed_tDBOutput_5", true);
				if (commitCounter_tDBOutput_5 > 0 && rowsToCommitCount_tDBOutput_5 != 0) {

				}
				conn_tDBOutput_5.commit();
				if (commitCounter_tDBOutput_5 > 0 && rowsToCommitCount_tDBOutput_5 != 0) {

					rowsToCommitCount_tDBOutput_5 = 0;
				}
				commitCounter_tDBOutput_5 = 0;

				conn_tDBOutput_5.close();

				resourceMap.put("finish_tDBOutput_5", true);

				nb_line_deleted_tDBOutput_5 = nb_line_deleted_tDBOutput_5 + deletedCount_tDBOutput_5;
				nb_line_update_tDBOutput_5 = nb_line_update_tDBOutput_5 + updatedCount_tDBOutput_5;
				nb_line_inserted_tDBOutput_5 = nb_line_inserted_tDBOutput_5 + insertedCount_tDBOutput_5;
				nb_line_rejected_tDBOutput_5 = nb_line_rejected_tDBOutput_5 + rejectedCount_tDBOutput_5;

				globalMap.put("tDBOutput_5_NB_LINE", nb_line_tDBOutput_5);
				globalMap.put("tDBOutput_5_NB_LINE_UPDATED", nb_line_update_tDBOutput_5);
				globalMap.put("tDBOutput_5_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_5);
				globalMap.put("tDBOutput_5_NB_LINE_DELETED", nb_line_deleted_tDBOutput_5);
				globalMap.put("tDBOutput_5_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_5);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "Teemp");
				}

				ok_Hash.put("tDBOutput_5", true);
				end_Hash.put("tDBOutput_5", System.currentTimeMillis());

				/**
				 * [tDBOutput_5 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBInput_1 finally ] start
				 */

				currentComponent = "tDBInput_1";

				/**
				 * [tDBInput_1 finally ] stop
				 */

				/**
				 * [tMap_6 finally ] start
				 */

				currentComponent = "tMap_6";

				/**
				 * [tMap_6 finally ] stop
				 */

				/**
				 * [tDBOutput_5 finally ] start
				 */

				currentComponent = "tDBOutput_5";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_5") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_5 = null;
						if ((pstmtToClose_tDBOutput_5 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_5")) != null) {
							pstmtToClose_tDBOutput_5.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_5") == null) {
						java.sql.Connection ctn_tDBOutput_5 = null;
						if ((ctn_tDBOutput_5 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_5")) != null) {
							try {
								ctn_tDBOutput_5.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_5) {
								String errorMessage_tDBOutput_5 = "failed to close the connection in tDBOutput_5 :"
										+ sqlEx_tDBOutput_5.getMessage();
								System.err.println(errorMessage_tDBOutput_5);
							}
						}
					}
				}

				/**
				 * [tDBOutput_5 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_1_SUBPROCESS_STATE", 1);
	}

	public static class VendeurStruct implements routines.system.IPersistableRow<VendeurStruct> {
		final static byte[] commonByteArrayLock_LOCAL_PROJECT_Dim_temp = new byte[0];
		static byte[] commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int ID_Vendeur;

		public int getID_Vendeur() {
			return this.ID_Vendeur;
		}

		public String adresse;

		public String getAdresse() {
			return this.adresse;
		}

		public String telephone;

		public String getTelephone() {
			return this.telephone;
		}

		public String email;

		public String getEmail() {
			return this.email;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + (int) this.ID_Vendeur;

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final VendeurStruct other = (VendeurStruct) obj;

			if (this.ID_Vendeur != other.ID_Vendeur)
				return false;

			return true;
		}

		public void copyDataTo(VendeurStruct other) {

			other.ID_Vendeur = this.ID_Vendeur;
			other.adresse = this.adresse;
			other.telephone = this.telephone;
			other.email = this.email;

		}

		public void copyKeysDataTo(VendeurStruct other) {

			other.ID_Vendeur = this.ID_Vendeur;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_LOCAL_PROJECT_Dim_temp.length) {
					if (length < 1024 && commonByteArray_LOCAL_PROJECT_Dim_temp.length == 0) {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[1024];
					} else {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length);
				strReturn = new String(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_LOCAL_PROJECT_Dim_temp.length) {
					if (length < 1024 && commonByteArray_LOCAL_PROJECT_Dim_temp.length == 0) {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[1024];
					} else {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length);
				strReturn = new String(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_LOCAL_PROJECT_Dim_temp) {

				try {

					int length = 0;

					this.ID_Vendeur = dis.readInt();

					this.adresse = readString(dis);

					this.telephone = readString(dis);

					this.email = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_LOCAL_PROJECT_Dim_temp) {

				try {

					int length = 0;

					this.ID_Vendeur = dis.readInt();

					this.adresse = readString(dis);

					this.telephone = readString(dis);

					this.email = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.ID_Vendeur);

				// String

				writeString(this.adresse, dos);

				// String

				writeString(this.telephone, dos);

				// String

				writeString(this.email, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.ID_Vendeur);

				// String

				writeString(this.adresse, dos);

				// String

				writeString(this.telephone, dos);

				// String

				writeString(this.email, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID_Vendeur=" + String.valueOf(ID_Vendeur));
			sb.append(",adresse=" + adresse);
			sb.append(",telephone=" + telephone);
			sb.append(",email=" + email);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(VendeurStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ID_Vendeur, other.ID_Vendeur);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_LOCAL_PROJECT_Dim_temp = new byte[0];
		static byte[] commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[0];

		public int ID_Vendeur;

		public int getID_Vendeur() {
			return this.ID_Vendeur;
		}

		public String adresse;

		public String getAdresse() {
			return this.adresse;
		}

		public String telephone;

		public String getTelephone() {
			return this.telephone;
		}

		public String email;

		public String getEmail() {
			return this.email;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_LOCAL_PROJECT_Dim_temp.length) {
					if (length < 1024 && commonByteArray_LOCAL_PROJECT_Dim_temp.length == 0) {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[1024];
					} else {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length);
				strReturn = new String(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_LOCAL_PROJECT_Dim_temp.length) {
					if (length < 1024 && commonByteArray_LOCAL_PROJECT_Dim_temp.length == 0) {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[1024];
					} else {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length);
				strReturn = new String(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_LOCAL_PROJECT_Dim_temp) {

				try {

					int length = 0;

					this.ID_Vendeur = dis.readInt();

					this.adresse = readString(dis);

					this.telephone = readString(dis);

					this.email = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_LOCAL_PROJECT_Dim_temp) {

				try {

					int length = 0;

					this.ID_Vendeur = dis.readInt();

					this.adresse = readString(dis);

					this.telephone = readString(dis);

					this.email = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.ID_Vendeur);

				// String

				writeString(this.adresse, dos);

				// String

				writeString(this.telephone, dos);

				// String

				writeString(this.email, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.ID_Vendeur);

				// String

				writeString(this.adresse, dos);

				// String

				writeString(this.telephone, dos);

				// String

				writeString(this.email, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID_Vendeur=" + String.valueOf(ID_Vendeur));
			sb.append(",adresse=" + adresse);
			sb.append(",telephone=" + telephone);
			sb.append(",email=" + email);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_2_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row1Struct row1 = new row1Struct();
				VendeurStruct Vendeur = new VendeurStruct();

				/**
				 * [tDBOutput_1 begin ] start
				 */

				ok_Hash.put("tDBOutput_1", false);
				start_Hash.put("tDBOutput_1", System.currentTimeMillis());

				currentComponent = "tDBOutput_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "Vendeur");
				}

				int tos_count_tDBOutput_1 = 0;

				int nb_line_tDBOutput_1 = 0;
				int nb_line_update_tDBOutput_1 = 0;
				int nb_line_inserted_tDBOutput_1 = 0;
				int nb_line_deleted_tDBOutput_1 = 0;
				int nb_line_rejected_tDBOutput_1 = 0;

				int deletedCount_tDBOutput_1 = 0;
				int updatedCount_tDBOutput_1 = 0;
				int insertedCount_tDBOutput_1 = 0;
				int rowsToCommitCount_tDBOutput_1 = 0;
				int rejectedCount_tDBOutput_1 = 0;

				String tableName_tDBOutput_1 = "Dim_Vendeur";
				boolean whetherReject_tDBOutput_1 = false;

				java.util.Calendar calendar_tDBOutput_1 = java.util.Calendar.getInstance();
				calendar_tDBOutput_1.set(1, 0, 1, 0, 0, 0);
				long year1_tDBOutput_1 = calendar_tDBOutput_1.getTime().getTime();
				calendar_tDBOutput_1.set(10000, 0, 1, 0, 0, 0);
				long year10000_tDBOutput_1 = calendar_tDBOutput_1.getTime().getTime();
				long date_tDBOutput_1;

				java.sql.Connection conn_tDBOutput_1 = null;

				String properties_tDBOutput_1 = "noDatetimeStringSync=true";
				if (properties_tDBOutput_1 == null || properties_tDBOutput_1.trim().length() == 0) {
					properties_tDBOutput_1 = "rewriteBatchedStatements=true&allowLoadLocalInfile=true";
				} else {
					if (!properties_tDBOutput_1.contains("rewriteBatchedStatements=")) {
						properties_tDBOutput_1 += "&rewriteBatchedStatements=true";
					}

					if (!properties_tDBOutput_1.contains("allowLoadLocalInfile=")) {
						properties_tDBOutput_1 += "&allowLoadLocalInfile=true";
					}
				}

				String url_tDBOutput_1 = "jdbc:mysql://" + "127.0.0.1" + ":" + "3306" + "/" + "Cube_olap" + "?"
						+ properties_tDBOutput_1;

				String driverClass_tDBOutput_1 = "com.mysql.jdbc.Driver";

				String dbUser_tDBOutput_1 = "root";

				final String decryptedPassword_tDBOutput_1 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:q/OnFmw1SyewULXA4PB0gEX/MXu1GYbSyV+27rHep5YhVizbdc7Z6kZTXa0=");

				String dbPwd_tDBOutput_1 = decryptedPassword_tDBOutput_1;
				java.lang.Class.forName(driverClass_tDBOutput_1);

				conn_tDBOutput_1 = java.sql.DriverManager.getConnection(url_tDBOutput_1, dbUser_tDBOutput_1,
						dbPwd_tDBOutput_1);

				resourceMap.put("conn_tDBOutput_1", conn_tDBOutput_1);
				conn_tDBOutput_1.setAutoCommit(false);
				int commitEvery_tDBOutput_1 = 10000;
				int commitCounter_tDBOutput_1 = 0;

				int count_tDBOutput_1 = 0;

				java.sql.DatabaseMetaData dbMetaData_tDBOutput_1 = conn_tDBOutput_1.getMetaData();
				java.sql.ResultSet rsTable_tDBOutput_1 = dbMetaData_tDBOutput_1.getTables("Cube_olap", null, null,
						new String[] { "TABLE" });
				boolean whetherExist_tDBOutput_1 = false;
				while (rsTable_tDBOutput_1.next()) {
					String table_tDBOutput_1 = rsTable_tDBOutput_1.getString("TABLE_NAME");
					if (table_tDBOutput_1.equalsIgnoreCase("Dim_Vendeur")) {
						whetherExist_tDBOutput_1 = true;
						break;
					}
				}
				if (whetherExist_tDBOutput_1) {
					try (java.sql.Statement stmtDrop_tDBOutput_1 = conn_tDBOutput_1.createStatement()) {
						stmtDrop_tDBOutput_1.execute("DROP TABLE `" + tableName_tDBOutput_1 + "`");
					}
				}
				try (java.sql.Statement stmtCreate_tDBOutput_1 = conn_tDBOutput_1.createStatement()) {
					stmtCreate_tDBOutput_1.execute("CREATE TABLE `" + tableName_tDBOutput_1
							+ "`(`ID_Vendeur` INT(10)   not null ,`adresse` VARCHAR(999)  ,`telephone` VARCHAR(999)  ,`email` VARCHAR(999)  ,primary key(`ID_Vendeur`))");
				}

				String insert_tDBOutput_1 = "INSERT INTO `" + "Dim_Vendeur"
						+ "` (`ID_Vendeur`,`adresse`,`telephone`,`email`) VALUES (?,?,?,?)";
				int batchSize_tDBOutput_1 = 100;
				int batchSizeCounter_tDBOutput_1 = 0;

				java.sql.PreparedStatement pstmt_tDBOutput_1 = conn_tDBOutput_1.prepareStatement(insert_tDBOutput_1);
				resourceMap.put("pstmt_tDBOutput_1", pstmt_tDBOutput_1);

				/**
				 * [tDBOutput_1 begin ] stop
				 */

				/**
				 * [tMap_2 begin ] start
				 */

				ok_Hash.put("tMap_2", false);
				start_Hash.put("tMap_2", System.currentTimeMillis());

				currentComponent = "tMap_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
				}

				int tos_count_tMap_2 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_2__Struct {
				}
				Var__tMap_2__Struct Var__tMap_2 = new Var__tMap_2__Struct();
// ###############################

// ###############################
// # Outputs initialization
				VendeurStruct Vendeur_tmp = new VendeurStruct();
// ###############################

				/**
				 * [tMap_2 begin ] stop
				 */

				/**
				 * [tDBInput_2 begin ] start
				 */

				ok_Hash.put("tDBInput_2", false);
				start_Hash.put("tDBInput_2", System.currentTimeMillis());

				currentComponent = "tDBInput_2";

				int tos_count_tDBInput_2 = 0;

				java.util.Calendar calendar_tDBInput_2 = java.util.Calendar.getInstance();
				calendar_tDBInput_2.set(0, 0, 0, 0, 0, 0);
				java.util.Date year0_tDBInput_2 = calendar_tDBInput_2.getTime();
				int nb_line_tDBInput_2 = 0;
				java.sql.Connection conn_tDBInput_2 = null;
				String driverClass_tDBInput_2 = "com.mysql.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_2 = java.lang.Class.forName(driverClass_tDBInput_2);
				String dbUser_tDBInput_2 = "root";

				final String decryptedPassword_tDBInput_2 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:P/q5ZdXtJ5RChsQ2flJx3hckt5ypfR/9RSXPXFh6Qb0VmS1fb+ANlcWVpK0=");

				String dbPwd_tDBInput_2 = decryptedPassword_tDBInput_2;

				String properties_tDBInput_2 = "noDatetimeStringSync=true";
				if (properties_tDBInput_2 == null || properties_tDBInput_2.trim().length() == 0) {
					properties_tDBInput_2 = "";
				}
				String url_tDBInput_2 = "jdbc:mysql://" + "127.0.0.1" + ":" + "3306" + "/" + "data_warehouse_vente"
						+ "?" + properties_tDBInput_2;

				conn_tDBInput_2 = java.sql.DriverManager.getConnection(url_tDBInput_2, dbUser_tDBInput_2,
						dbPwd_tDBInput_2);

				java.sql.Statement stmt_tDBInput_2 = conn_tDBInput_2.createStatement();

				String dbquery_tDBInput_2 = "SELECT \n  `vendeur`.`ID_Vendeur`, \n  `vendeur`.`adresse`, \n  `vendeur`.`telephone`, \n  `vendeur`.`email`\nFROM `vendeur`"
						+ "";

				globalMap.put("tDBInput_2_QUERY", dbquery_tDBInput_2);
				java.sql.ResultSet rs_tDBInput_2 = null;

				try {
					rs_tDBInput_2 = stmt_tDBInput_2.executeQuery(dbquery_tDBInput_2);
					java.sql.ResultSetMetaData rsmd_tDBInput_2 = rs_tDBInput_2.getMetaData();
					int colQtyInRs_tDBInput_2 = rsmd_tDBInput_2.getColumnCount();

					String tmpContent_tDBInput_2 = null;

					while (rs_tDBInput_2.next()) {
						nb_line_tDBInput_2++;

						if (colQtyInRs_tDBInput_2 < 1) {
							row1.ID_Vendeur = 0;
						} else {

							row1.ID_Vendeur = rs_tDBInput_2.getInt(1);
							if (rs_tDBInput_2.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_2 < 2) {
							row1.adresse = null;
						} else {

							row1.adresse = routines.system.JDBCUtil.getString(rs_tDBInput_2, 2, false);
						}
						if (colQtyInRs_tDBInput_2 < 3) {
							row1.telephone = null;
						} else {

							row1.telephone = routines.system.JDBCUtil.getString(rs_tDBInput_2, 3, false);
						}
						if (colQtyInRs_tDBInput_2 < 4) {
							row1.email = null;
						} else {

							row1.email = routines.system.JDBCUtil.getString(rs_tDBInput_2, 4, false);
						}

						/**
						 * [tDBInput_2 begin ] stop
						 */

						/**
						 * [tDBInput_2 main ] start
						 */

						currentComponent = "tDBInput_2";

						tos_count_tDBInput_2++;

						/**
						 * [tDBInput_2 main ] stop
						 */

						/**
						 * [tDBInput_2 process_data_begin ] start
						 */

						currentComponent = "tDBInput_2";

						/**
						 * [tDBInput_2 process_data_begin ] stop
						 */

						/**
						 * [tMap_2 main ] start
						 */

						currentComponent = "tMap_2";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row1"

							);
						}

						boolean hasCasePrimitiveKeyWithNull_tMap_2 = false;

						// ###############################
						// # Input tables (lookups)
						boolean rejectedInnerJoin_tMap_2 = false;
						boolean mainRowRejected_tMap_2 = false;

						// ###############################
						{ // start of Var scope

							// ###############################
							// # Vars tables

							Var__tMap_2__Struct Var = Var__tMap_2;// ###############################
							// ###############################
							// # Output tables

							Vendeur = null;

// # Output table : 'Vendeur'
							Vendeur_tmp.ID_Vendeur = row1.ID_Vendeur;
							Vendeur_tmp.adresse = row1.adresse;
							Vendeur_tmp.telephone = row1.telephone;
							Vendeur_tmp.email = row1.email;
							Vendeur = Vendeur_tmp;
// ###############################

						} // end of Var scope

						rejectedInnerJoin_tMap_2 = false;

						tos_count_tMap_2++;

						/**
						 * [tMap_2 main ] stop
						 */

						/**
						 * [tMap_2 process_data_begin ] start
						 */

						currentComponent = "tMap_2";

						/**
						 * [tMap_2 process_data_begin ] stop
						 */
// Start of branch "Vendeur"
						if (Vendeur != null) {

							/**
							 * [tDBOutput_1 main ] start
							 */

							currentComponent = "tDBOutput_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "Vendeur"

								);
							}

							whetherReject_tDBOutput_1 = false;
							pstmt_tDBOutput_1.setInt(1, Vendeur.ID_Vendeur);

							if (Vendeur.adresse == null) {
								pstmt_tDBOutput_1.setNull(2, java.sql.Types.VARCHAR);
							} else {
								pstmt_tDBOutput_1.setString(2, Vendeur.adresse);
							}

							if (Vendeur.telephone == null) {
								pstmt_tDBOutput_1.setNull(3, java.sql.Types.VARCHAR);
							} else {
								pstmt_tDBOutput_1.setString(3, Vendeur.telephone);
							}

							if (Vendeur.email == null) {
								pstmt_tDBOutput_1.setNull(4, java.sql.Types.VARCHAR);
							} else {
								pstmt_tDBOutput_1.setString(4, Vendeur.email);
							}

							pstmt_tDBOutput_1.addBatch();
							nb_line_tDBOutput_1++;

							batchSizeCounter_tDBOutput_1++;
							if (batchSize_tDBOutput_1 <= batchSizeCounter_tDBOutput_1) {
								try {
									int countSum_tDBOutput_1 = 0;
									for (int countEach_tDBOutput_1 : pstmt_tDBOutput_1.executeBatch()) {
										countSum_tDBOutput_1 += (countEach_tDBOutput_1 == java.sql.Statement.EXECUTE_FAILED
												? 0
												: 1);
									}
									rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
									insertedCount_tDBOutput_1 += countSum_tDBOutput_1;
								} catch (java.sql.BatchUpdateException e) {
									globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());
									int countSum_tDBOutput_1 = 0;
									for (int countEach_tDBOutput_1 : e.getUpdateCounts()) {
										countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0 : countEach_tDBOutput_1);
									}
									rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
									insertedCount_tDBOutput_1 += countSum_tDBOutput_1;
									System.err.println(e.getMessage());
								}

								batchSizeCounter_tDBOutput_1 = 0;
							}
							commitCounter_tDBOutput_1++;

							if (commitEvery_tDBOutput_1 <= commitCounter_tDBOutput_1) {

								try {
									int countSum_tDBOutput_1 = 0;
									for (int countEach_tDBOutput_1 : pstmt_tDBOutput_1.executeBatch()) {
										countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0 : 1);
									}
									rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
									insertedCount_tDBOutput_1 += countSum_tDBOutput_1;
								} catch (java.sql.BatchUpdateException e) {
									globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());
									int countSum_tDBOutput_1 = 0;
									for (int countEach_tDBOutput_1 : e.getUpdateCounts()) {
										countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0 : countEach_tDBOutput_1);
									}
									rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
									insertedCount_tDBOutput_1 += countSum_tDBOutput_1;
									System.err.println(e.getMessage());

								}
								if (rowsToCommitCount_tDBOutput_1 != 0) {
								}
								conn_tDBOutput_1.commit();
								if (rowsToCommitCount_tDBOutput_1 != 0) {
									rowsToCommitCount_tDBOutput_1 = 0;
								}
								commitCounter_tDBOutput_1 = 0;

							}

							tos_count_tDBOutput_1++;

							/**
							 * [tDBOutput_1 main ] stop
							 */

							/**
							 * [tDBOutput_1 process_data_begin ] start
							 */

							currentComponent = "tDBOutput_1";

							/**
							 * [tDBOutput_1 process_data_begin ] stop
							 */

							/**
							 * [tDBOutput_1 process_data_end ] start
							 */

							currentComponent = "tDBOutput_1";

							/**
							 * [tDBOutput_1 process_data_end ] stop
							 */

						} // End of branch "Vendeur"

						/**
						 * [tMap_2 process_data_end ] start
						 */

						currentComponent = "tMap_2";

						/**
						 * [tMap_2 process_data_end ] stop
						 */

						/**
						 * [tDBInput_2 process_data_end ] start
						 */

						currentComponent = "tDBInput_2";

						/**
						 * [tDBInput_2 process_data_end ] stop
						 */

						/**
						 * [tDBInput_2 end ] start
						 */

						currentComponent = "tDBInput_2";

					}
				} finally {
					if (rs_tDBInput_2 != null) {
						rs_tDBInput_2.close();
					}
					if (stmt_tDBInput_2 != null) {
						stmt_tDBInput_2.close();
					}
					if (conn_tDBInput_2 != null && !conn_tDBInput_2.isClosed()) {

						conn_tDBInput_2.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}

				}

				globalMap.put("tDBInput_2_NB_LINE", nb_line_tDBInput_2);

				ok_Hash.put("tDBInput_2", true);
				end_Hash.put("tDBInput_2", System.currentTimeMillis());

				/**
				 * [tDBInput_2 end ] stop
				 */

				/**
				 * [tMap_2 end ] start
				 */

				currentComponent = "tMap_2";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
				}

				ok_Hash.put("tMap_2", true);
				end_Hash.put("tMap_2", System.currentTimeMillis());

				/**
				 * [tMap_2 end ] stop
				 */

				/**
				 * [tDBOutput_1 end ] start
				 */

				currentComponent = "tDBOutput_1";

				try {
					if (batchSizeCounter_tDBOutput_1 != 0) {
						int countSum_tDBOutput_1 = 0;

						for (int countEach_tDBOutput_1 : pstmt_tDBOutput_1.executeBatch()) {
							countSum_tDBOutput_1 += (countEach_tDBOutput_1 == java.sql.Statement.EXECUTE_FAILED ? 0
									: 1);
						}
						rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;

						insertedCount_tDBOutput_1 += countSum_tDBOutput_1;

					}

				} catch (java.sql.BatchUpdateException e) {
					globalMap.put(currentComponent + "_ERROR_MESSAGE", e.getMessage());

					int countSum_tDBOutput_1 = 0;
					for (int countEach_tDBOutput_1 : e.getUpdateCounts()) {
						countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0 : countEach_tDBOutput_1);
					}
					rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;

					insertedCount_tDBOutput_1 += countSum_tDBOutput_1;

					System.err.println(e.getMessage());

				}
				batchSizeCounter_tDBOutput_1 = 0;

				if (pstmt_tDBOutput_1 != null) {

					pstmt_tDBOutput_1.close();
					resourceMap.remove("pstmt_tDBOutput_1");

				}
				resourceMap.put("statementClosed_tDBOutput_1", true);
				if (commitCounter_tDBOutput_1 > 0 && rowsToCommitCount_tDBOutput_1 != 0) {

				}
				conn_tDBOutput_1.commit();
				if (commitCounter_tDBOutput_1 > 0 && rowsToCommitCount_tDBOutput_1 != 0) {

					rowsToCommitCount_tDBOutput_1 = 0;
				}
				commitCounter_tDBOutput_1 = 0;

				conn_tDBOutput_1.close();

				resourceMap.put("finish_tDBOutput_1", true);

				nb_line_deleted_tDBOutput_1 = nb_line_deleted_tDBOutput_1 + deletedCount_tDBOutput_1;
				nb_line_update_tDBOutput_1 = nb_line_update_tDBOutput_1 + updatedCount_tDBOutput_1;
				nb_line_inserted_tDBOutput_1 = nb_line_inserted_tDBOutput_1 + insertedCount_tDBOutput_1;
				nb_line_rejected_tDBOutput_1 = nb_line_rejected_tDBOutput_1 + rejectedCount_tDBOutput_1;

				globalMap.put("tDBOutput_1_NB_LINE", nb_line_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_UPDATED", nb_line_update_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_DELETED", nb_line_deleted_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_1);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "Vendeur");
				}

				ok_Hash.put("tDBOutput_1", true);
				end_Hash.put("tDBOutput_1", System.currentTimeMillis());

				/**
				 * [tDBOutput_1 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBInput_2 finally ] start
				 */

				currentComponent = "tDBInput_2";

				/**
				 * [tDBInput_2 finally ] stop
				 */

				/**
				 * [tMap_2 finally ] start
				 */

				currentComponent = "tMap_2";

				/**
				 * [tMap_2 finally ] stop
				 */

				/**
				 * [tDBOutput_1 finally ] start
				 */

				currentComponent = "tDBOutput_1";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_1") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_1 = null;
						if ((pstmtToClose_tDBOutput_1 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_1")) != null) {
							pstmtToClose_tDBOutput_1.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_1") == null) {
						java.sql.Connection ctn_tDBOutput_1 = null;
						if ((ctn_tDBOutput_1 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_1")) != null) {
							try {
								ctn_tDBOutput_1.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_1) {
								String errorMessage_tDBOutput_1 = "failed to close the connection in tDBOutput_1 :"
										+ sqlEx_tDBOutput_1.getMessage();
								System.err.println(errorMessage_tDBOutput_1);
							}
						}
					}
				}

				/**
				 * [tDBOutput_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_2_SUBPROCESS_STATE", 1);
	}

	public static class ProduitStruct implements routines.system.IPersistableRow<ProduitStruct> {
		final static byte[] commonByteArrayLock_LOCAL_PROJECT_Dim_temp = new byte[0];
		static byte[] commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int ID_Produit;

		public int getID_Produit() {
			return this.ID_Produit;
		}

		public String Nom_Produit;

		public String getNom_Produit() {
			return this.Nom_Produit;
		}

		public String Prix_Unitaire;

		public String getPrix_Unitaire() {
			return this.Prix_Unitaire;
		}

		public String ID_Categorie;

		public String getID_Categorie() {
			return this.ID_Categorie;
		}

		public String ID_Bois;

		public String getID_Bois() {
			return this.ID_Bois;
		}

		public String Longueur;

		public String getLongueur() {
			return this.Longueur;
		}

		public String Largeur;

		public String getLargeur() {
			return this.Largeur;
		}

		public String Couleur;

		public String getCouleur() {
			return this.Couleur;
		}

		public String Stock_Disponib;

		public String getStock_Disponib() {
			return this.Stock_Disponib;
		}

		public String Date_Ajout;

		public String getDate_Ajout() {
			return this.Date_Ajout;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + (int) this.ID_Produit;

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final ProduitStruct other = (ProduitStruct) obj;

			if (this.ID_Produit != other.ID_Produit)
				return false;

			return true;
		}

		public void copyDataTo(ProduitStruct other) {

			other.ID_Produit = this.ID_Produit;
			other.Nom_Produit = this.Nom_Produit;
			other.Prix_Unitaire = this.Prix_Unitaire;
			other.ID_Categorie = this.ID_Categorie;
			other.ID_Bois = this.ID_Bois;
			other.Longueur = this.Longueur;
			other.Largeur = this.Largeur;
			other.Couleur = this.Couleur;
			other.Stock_Disponib = this.Stock_Disponib;
			other.Date_Ajout = this.Date_Ajout;

		}

		public void copyKeysDataTo(ProduitStruct other) {

			other.ID_Produit = this.ID_Produit;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_LOCAL_PROJECT_Dim_temp.length) {
					if (length < 1024 && commonByteArray_LOCAL_PROJECT_Dim_temp.length == 0) {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[1024];
					} else {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length);
				strReturn = new String(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_LOCAL_PROJECT_Dim_temp.length) {
					if (length < 1024 && commonByteArray_LOCAL_PROJECT_Dim_temp.length == 0) {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[1024];
					} else {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length);
				strReturn = new String(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_LOCAL_PROJECT_Dim_temp) {

				try {

					int length = 0;

					this.ID_Produit = dis.readInt();

					this.Nom_Produit = readString(dis);

					this.Prix_Unitaire = readString(dis);

					this.ID_Categorie = readString(dis);

					this.ID_Bois = readString(dis);

					this.Longueur = readString(dis);

					this.Largeur = readString(dis);

					this.Couleur = readString(dis);

					this.Stock_Disponib = readString(dis);

					this.Date_Ajout = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_LOCAL_PROJECT_Dim_temp) {

				try {

					int length = 0;

					this.ID_Produit = dis.readInt();

					this.Nom_Produit = readString(dis);

					this.Prix_Unitaire = readString(dis);

					this.ID_Categorie = readString(dis);

					this.ID_Bois = readString(dis);

					this.Longueur = readString(dis);

					this.Largeur = readString(dis);

					this.Couleur = readString(dis);

					this.Stock_Disponib = readString(dis);

					this.Date_Ajout = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.ID_Produit);

				// String

				writeString(this.Nom_Produit, dos);

				// String

				writeString(this.Prix_Unitaire, dos);

				// String

				writeString(this.ID_Categorie, dos);

				// String

				writeString(this.ID_Bois, dos);

				// String

				writeString(this.Longueur, dos);

				// String

				writeString(this.Largeur, dos);

				// String

				writeString(this.Couleur, dos);

				// String

				writeString(this.Stock_Disponib, dos);

				// String

				writeString(this.Date_Ajout, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.ID_Produit);

				// String

				writeString(this.Nom_Produit, dos);

				// String

				writeString(this.Prix_Unitaire, dos);

				// String

				writeString(this.ID_Categorie, dos);

				// String

				writeString(this.ID_Bois, dos);

				// String

				writeString(this.Longueur, dos);

				// String

				writeString(this.Largeur, dos);

				// String

				writeString(this.Couleur, dos);

				// String

				writeString(this.Stock_Disponib, dos);

				// String

				writeString(this.Date_Ajout, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID_Produit=" + String.valueOf(ID_Produit));
			sb.append(",Nom_Produit=" + Nom_Produit);
			sb.append(",Prix_Unitaire=" + Prix_Unitaire);
			sb.append(",ID_Categorie=" + ID_Categorie);
			sb.append(",ID_Bois=" + ID_Bois);
			sb.append(",Longueur=" + Longueur);
			sb.append(",Largeur=" + Largeur);
			sb.append(",Couleur=" + Couleur);
			sb.append(",Stock_Disponib=" + Stock_Disponib);
			sb.append(",Date_Ajout=" + Date_Ajout);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(ProduitStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ID_Produit, other.ID_Produit);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
		final static byte[] commonByteArrayLock_LOCAL_PROJECT_Dim_temp = new byte[0];
		static byte[] commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[0];

		public int ID_Produit;

		public int getID_Produit() {
			return this.ID_Produit;
		}

		public String Nom_Produit;

		public String getNom_Produit() {
			return this.Nom_Produit;
		}

		public String Prix_Unitaire;

		public String getPrix_Unitaire() {
			return this.Prix_Unitaire;
		}

		public String ID_Categorie;

		public String getID_Categorie() {
			return this.ID_Categorie;
		}

		public String ID_Bois;

		public String getID_Bois() {
			return this.ID_Bois;
		}

		public String Longueur;

		public String getLongueur() {
			return this.Longueur;
		}

		public String Largeur;

		public String getLargeur() {
			return this.Largeur;
		}

		public String Couleur;

		public String getCouleur() {
			return this.Couleur;
		}

		public String Stock_Disponib;

		public String getStock_Disponib() {
			return this.Stock_Disponib;
		}

		public String Date_Ajout;

		public String getDate_Ajout() {
			return this.Date_Ajout;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_LOCAL_PROJECT_Dim_temp.length) {
					if (length < 1024 && commonByteArray_LOCAL_PROJECT_Dim_temp.length == 0) {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[1024];
					} else {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length);
				strReturn = new String(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_LOCAL_PROJECT_Dim_temp.length) {
					if (length < 1024 && commonByteArray_LOCAL_PROJECT_Dim_temp.length == 0) {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[1024];
					} else {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length);
				strReturn = new String(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_LOCAL_PROJECT_Dim_temp) {

				try {

					int length = 0;

					this.ID_Produit = dis.readInt();

					this.Nom_Produit = readString(dis);

					this.Prix_Unitaire = readString(dis);

					this.ID_Categorie = readString(dis);

					this.ID_Bois = readString(dis);

					this.Longueur = readString(dis);

					this.Largeur = readString(dis);

					this.Couleur = readString(dis);

					this.Stock_Disponib = readString(dis);

					this.Date_Ajout = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_LOCAL_PROJECT_Dim_temp) {

				try {

					int length = 0;

					this.ID_Produit = dis.readInt();

					this.Nom_Produit = readString(dis);

					this.Prix_Unitaire = readString(dis);

					this.ID_Categorie = readString(dis);

					this.ID_Bois = readString(dis);

					this.Longueur = readString(dis);

					this.Largeur = readString(dis);

					this.Couleur = readString(dis);

					this.Stock_Disponib = readString(dis);

					this.Date_Ajout = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.ID_Produit);

				// String

				writeString(this.Nom_Produit, dos);

				// String

				writeString(this.Prix_Unitaire, dos);

				// String

				writeString(this.ID_Categorie, dos);

				// String

				writeString(this.ID_Bois, dos);

				// String

				writeString(this.Longueur, dos);

				// String

				writeString(this.Largeur, dos);

				// String

				writeString(this.Couleur, dos);

				// String

				writeString(this.Stock_Disponib, dos);

				// String

				writeString(this.Date_Ajout, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.ID_Produit);

				// String

				writeString(this.Nom_Produit, dos);

				// String

				writeString(this.Prix_Unitaire, dos);

				// String

				writeString(this.ID_Categorie, dos);

				// String

				writeString(this.ID_Bois, dos);

				// String

				writeString(this.Longueur, dos);

				// String

				writeString(this.Largeur, dos);

				// String

				writeString(this.Couleur, dos);

				// String

				writeString(this.Stock_Disponib, dos);

				// String

				writeString(this.Date_Ajout, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID_Produit=" + String.valueOf(ID_Produit));
			sb.append(",Nom_Produit=" + Nom_Produit);
			sb.append(",Prix_Unitaire=" + Prix_Unitaire);
			sb.append(",ID_Categorie=" + ID_Categorie);
			sb.append(",ID_Bois=" + ID_Bois);
			sb.append(",Longueur=" + Longueur);
			sb.append(",Largeur=" + Largeur);
			sb.append(",Couleur=" + Couleur);
			sb.append(",Stock_Disponib=" + Stock_Disponib);
			sb.append(",Date_Ajout=" + Date_Ajout);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row3Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_3Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_3_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row3Struct row3 = new row3Struct();
				ProduitStruct Produit = new ProduitStruct();

				/**
				 * [tDBOutput_3 begin ] start
				 */

				ok_Hash.put("tDBOutput_3", false);
				start_Hash.put("tDBOutput_3", System.currentTimeMillis());

				currentComponent = "tDBOutput_3";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "Produit");
				}

				int tos_count_tDBOutput_3 = 0;

				int nb_line_tDBOutput_3 = 0;
				int nb_line_update_tDBOutput_3 = 0;
				int nb_line_inserted_tDBOutput_3 = 0;
				int nb_line_deleted_tDBOutput_3 = 0;
				int nb_line_rejected_tDBOutput_3 = 0;

				int deletedCount_tDBOutput_3 = 0;
				int updatedCount_tDBOutput_3 = 0;
				int insertedCount_tDBOutput_3 = 0;
				int rowsToCommitCount_tDBOutput_3 = 0;
				int rejectedCount_tDBOutput_3 = 0;

				String tableName_tDBOutput_3 = "Dim_Produit";
				boolean whetherReject_tDBOutput_3 = false;

				java.util.Calendar calendar_tDBOutput_3 = java.util.Calendar.getInstance();
				calendar_tDBOutput_3.set(1, 0, 1, 0, 0, 0);
				long year1_tDBOutput_3 = calendar_tDBOutput_3.getTime().getTime();
				calendar_tDBOutput_3.set(10000, 0, 1, 0, 0, 0);
				long year10000_tDBOutput_3 = calendar_tDBOutput_3.getTime().getTime();
				long date_tDBOutput_3;

				java.sql.Connection conn_tDBOutput_3 = null;

				String properties_tDBOutput_3 = "noDatetimeStringSync=true";
				if (properties_tDBOutput_3 == null || properties_tDBOutput_3.trim().length() == 0) {
					properties_tDBOutput_3 = "rewriteBatchedStatements=true&allowLoadLocalInfile=true";
				} else {
					if (!properties_tDBOutput_3.contains("rewriteBatchedStatements=")) {
						properties_tDBOutput_3 += "&rewriteBatchedStatements=true";
					}

					if (!properties_tDBOutput_3.contains("allowLoadLocalInfile=")) {
						properties_tDBOutput_3 += "&allowLoadLocalInfile=true";
					}
				}

				String url_tDBOutput_3 = "jdbc:mysql://" + "127.0.0.1" + ":" + "3306" + "/" + "Cube_olap" + "?"
						+ properties_tDBOutput_3;

				String driverClass_tDBOutput_3 = "com.mysql.jdbc.Driver";

				String dbUser_tDBOutput_3 = "root";

				final String decryptedPassword_tDBOutput_3 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:kPolxUHOjirzelFBKCrLRH6mLe4A2lxamydjIN2iPty0pyC+Z2ORChN6w+E=");

				String dbPwd_tDBOutput_3 = decryptedPassword_tDBOutput_3;
				java.lang.Class.forName(driverClass_tDBOutput_3);

				conn_tDBOutput_3 = java.sql.DriverManager.getConnection(url_tDBOutput_3, dbUser_tDBOutput_3,
						dbPwd_tDBOutput_3);

				resourceMap.put("conn_tDBOutput_3", conn_tDBOutput_3);
				conn_tDBOutput_3.setAutoCommit(false);
				int commitEvery_tDBOutput_3 = 10000;
				int commitCounter_tDBOutput_3 = 0;

				int count_tDBOutput_3 = 0;

				java.sql.DatabaseMetaData dbMetaData_tDBOutput_3 = conn_tDBOutput_3.getMetaData();
				java.sql.ResultSet rsTable_tDBOutput_3 = dbMetaData_tDBOutput_3.getTables("Cube_olap", null, null,
						new String[] { "TABLE" });
				boolean whetherExist_tDBOutput_3 = false;
				while (rsTable_tDBOutput_3.next()) {
					String table_tDBOutput_3 = rsTable_tDBOutput_3.getString("TABLE_NAME");
					if (table_tDBOutput_3.equalsIgnoreCase("Dim_Produit")) {
						whetherExist_tDBOutput_3 = true;
						break;
					}
				}
				if (whetherExist_tDBOutput_3) {
					try (java.sql.Statement stmtDrop_tDBOutput_3 = conn_tDBOutput_3.createStatement()) {
						stmtDrop_tDBOutput_3.execute("DROP TABLE `" + tableName_tDBOutput_3 + "`");
					}
				}
				try (java.sql.Statement stmtCreate_tDBOutput_3 = conn_tDBOutput_3.createStatement()) {
					stmtCreate_tDBOutput_3.execute("CREATE TABLE `" + tableName_tDBOutput_3
							+ "`(`ID_Produit` INT(10)   not null ,`Nom_Produit` VARCHAR(999)  ,`Prix_Unitaire` VARCHAR(999)  ,`ID_Categorie` VARCHAR(999)  ,`ID_Bois` VARCHAR(999)  ,`Longueur` VARCHAR(999)  ,`Largeur` VARCHAR(999)  ,`Couleur` VARCHAR(999)  ,`Stock_Disponib` VARCHAR(999)  ,`Date_Ajout` VARCHAR(999)  ,primary key(`ID_Produit`))");
				}

				String insert_tDBOutput_3 = "INSERT INTO `" + "Dim_Produit"
						+ "` (`ID_Produit`,`Nom_Produit`,`Prix_Unitaire`,`ID_Categorie`,`ID_Bois`,`Longueur`,`Largeur`,`Couleur`,`Stock_Disponib`,`Date_Ajout`) VALUES (?,?,?,?,?,?,?,?,?,?)";
				int batchSize_tDBOutput_3 = 100;
				int batchSizeCounter_tDBOutput_3 = 0;

				java.sql.PreparedStatement pstmt_tDBOutput_3 = conn_tDBOutput_3.prepareStatement(insert_tDBOutput_3);
				resourceMap.put("pstmt_tDBOutput_3", pstmt_tDBOutput_3);

				/**
				 * [tDBOutput_3 begin ] stop
				 */

				/**
				 * [tMap_4 begin ] start
				 */

				ok_Hash.put("tMap_4", false);
				start_Hash.put("tMap_4", System.currentTimeMillis());

				currentComponent = "tMap_4";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row3");
				}

				int tos_count_tMap_4 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_4__Struct {
				}
				Var__tMap_4__Struct Var__tMap_4 = new Var__tMap_4__Struct();
// ###############################

// ###############################
// # Outputs initialization
				ProduitStruct Produit_tmp = new ProduitStruct();
// ###############################

				/**
				 * [tMap_4 begin ] stop
				 */

				/**
				 * [tDBInput_3 begin ] start
				 */

				ok_Hash.put("tDBInput_3", false);
				start_Hash.put("tDBInput_3", System.currentTimeMillis());

				currentComponent = "tDBInput_3";

				int tos_count_tDBInput_3 = 0;

				java.util.Calendar calendar_tDBInput_3 = java.util.Calendar.getInstance();
				calendar_tDBInput_3.set(0, 0, 0, 0, 0, 0);
				java.util.Date year0_tDBInput_3 = calendar_tDBInput_3.getTime();
				int nb_line_tDBInput_3 = 0;
				java.sql.Connection conn_tDBInput_3 = null;
				String driverClass_tDBInput_3 = "com.mysql.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_3 = java.lang.Class.forName(driverClass_tDBInput_3);
				String dbUser_tDBInput_3 = "root";

				final String decryptedPassword_tDBInput_3 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:0izJbqVoGVkxnROn/oHF5iYMCoaMH5NphG8HeAlPDZLTnAt7UX6WVAND9Xw=");

				String dbPwd_tDBInput_3 = decryptedPassword_tDBInput_3;

				String properties_tDBInput_3 = "noDatetimeStringSync=true";
				if (properties_tDBInput_3 == null || properties_tDBInput_3.trim().length() == 0) {
					properties_tDBInput_3 = "";
				}
				String url_tDBInput_3 = "jdbc:mysql://" + "127.0.0.1" + ":" + "3306" + "/" + "data_warehouse_vente"
						+ "?" + properties_tDBInput_3;

				conn_tDBInput_3 = java.sql.DriverManager.getConnection(url_tDBInput_3, dbUser_tDBInput_3,
						dbPwd_tDBInput_3);

				java.sql.Statement stmt_tDBInput_3 = conn_tDBInput_3.createStatement();

				String dbquery_tDBInput_3 = "SELECT \n  `produit`.`ID_Produit`, \n  `produit`.`Nom_Produit`, \n  `produit`.`Prix_Unitaire`, \n  `produit`.`ID_Categorie`"
						+ ", \n  `produit`.`ID_Bois`, \n  `produit`.`Longueur`, \n  `produit`.`Largeur`, \n  `produit`.`Couleur`, \n  `produit`.`Stock_D"
						+ "isponib`, \n  `produit`.`Date_Ajout`\nFROM `produit`";

				globalMap.put("tDBInput_3_QUERY", dbquery_tDBInput_3);
				java.sql.ResultSet rs_tDBInput_3 = null;

				try {
					rs_tDBInput_3 = stmt_tDBInput_3.executeQuery(dbquery_tDBInput_3);
					java.sql.ResultSetMetaData rsmd_tDBInput_3 = rs_tDBInput_3.getMetaData();
					int colQtyInRs_tDBInput_3 = rsmd_tDBInput_3.getColumnCount();

					String tmpContent_tDBInput_3 = null;

					while (rs_tDBInput_3.next()) {
						nb_line_tDBInput_3++;

						if (colQtyInRs_tDBInput_3 < 1) {
							row3.ID_Produit = 0;
						} else {

							row3.ID_Produit = rs_tDBInput_3.getInt(1);
							if (rs_tDBInput_3.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_3 < 2) {
							row3.Nom_Produit = null;
						} else {

							row3.Nom_Produit = routines.system.JDBCUtil.getString(rs_tDBInput_3, 2, false);
						}
						if (colQtyInRs_tDBInput_3 < 3) {
							row3.Prix_Unitaire = null;
						} else {

							row3.Prix_Unitaire = routines.system.JDBCUtil.getString(rs_tDBInput_3, 3, false);
						}
						if (colQtyInRs_tDBInput_3 < 4) {
							row3.ID_Categorie = null;
						} else {

							row3.ID_Categorie = routines.system.JDBCUtil.getString(rs_tDBInput_3, 4, false);
						}
						if (colQtyInRs_tDBInput_3 < 5) {
							row3.ID_Bois = null;
						} else {

							row3.ID_Bois = routines.system.JDBCUtil.getString(rs_tDBInput_3, 5, false);
						}
						if (colQtyInRs_tDBInput_3 < 6) {
							row3.Longueur = null;
						} else {

							row3.Longueur = routines.system.JDBCUtil.getString(rs_tDBInput_3, 6, false);
						}
						if (colQtyInRs_tDBInput_3 < 7) {
							row3.Largeur = null;
						} else {

							row3.Largeur = routines.system.JDBCUtil.getString(rs_tDBInput_3, 7, false);
						}
						if (colQtyInRs_tDBInput_3 < 8) {
							row3.Couleur = null;
						} else {

							row3.Couleur = routines.system.JDBCUtil.getString(rs_tDBInput_3, 8, false);
						}
						if (colQtyInRs_tDBInput_3 < 9) {
							row3.Stock_Disponib = null;
						} else {

							row3.Stock_Disponib = routines.system.JDBCUtil.getString(rs_tDBInput_3, 9, false);
						}
						if (colQtyInRs_tDBInput_3 < 10) {
							row3.Date_Ajout = null;
						} else {

							row3.Date_Ajout = routines.system.JDBCUtil.getString(rs_tDBInput_3, 10, false);
						}

						/**
						 * [tDBInput_3 begin ] stop
						 */

						/**
						 * [tDBInput_3 main ] start
						 */

						currentComponent = "tDBInput_3";

						tos_count_tDBInput_3++;

						/**
						 * [tDBInput_3 main ] stop
						 */

						/**
						 * [tDBInput_3 process_data_begin ] start
						 */

						currentComponent = "tDBInput_3";

						/**
						 * [tDBInput_3 process_data_begin ] stop
						 */

						/**
						 * [tMap_4 main ] start
						 */

						currentComponent = "tMap_4";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row3"

							);
						}

						boolean hasCasePrimitiveKeyWithNull_tMap_4 = false;

						// ###############################
						// # Input tables (lookups)
						boolean rejectedInnerJoin_tMap_4 = false;
						boolean mainRowRejected_tMap_4 = false;

						// ###############################
						{ // start of Var scope

							// ###############################
							// # Vars tables

							Var__tMap_4__Struct Var = Var__tMap_4;// ###############################
							// ###############################
							// # Output tables

							Produit = null;

// # Output table : 'Produit'
							Produit_tmp.ID_Produit = row3.ID_Produit;
							Produit_tmp.Nom_Produit = row3.Nom_Produit;
							Produit_tmp.Prix_Unitaire = row3.Prix_Unitaire;
							Produit_tmp.ID_Categorie = row3.ID_Categorie;
							Produit_tmp.ID_Bois = row3.ID_Bois;
							Produit_tmp.Longueur = row3.Longueur;
							Produit_tmp.Largeur = row3.Largeur;
							Produit_tmp.Couleur = row3.Couleur;
							Produit_tmp.Stock_Disponib = row3.Stock_Disponib;
							Produit_tmp.Date_Ajout = row3.Date_Ajout;
							Produit = Produit_tmp;
// ###############################

						} // end of Var scope

						rejectedInnerJoin_tMap_4 = false;

						tos_count_tMap_4++;

						/**
						 * [tMap_4 main ] stop
						 */

						/**
						 * [tMap_4 process_data_begin ] start
						 */

						currentComponent = "tMap_4";

						/**
						 * [tMap_4 process_data_begin ] stop
						 */
// Start of branch "Produit"
						if (Produit != null) {

							/**
							 * [tDBOutput_3 main ] start
							 */

							currentComponent = "tDBOutput_3";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "Produit"

								);
							}

							whetherReject_tDBOutput_3 = false;
							pstmt_tDBOutput_3.setInt(1, Produit.ID_Produit);

							if (Produit.Nom_Produit == null) {
								pstmt_tDBOutput_3.setNull(2, java.sql.Types.VARCHAR);
							} else {
								pstmt_tDBOutput_3.setString(2, Produit.Nom_Produit);
							}

							if (Produit.Prix_Unitaire == null) {
								pstmt_tDBOutput_3.setNull(3, java.sql.Types.VARCHAR);
							} else {
								pstmt_tDBOutput_3.setString(3, Produit.Prix_Unitaire);
							}

							if (Produit.ID_Categorie == null) {
								pstmt_tDBOutput_3.setNull(4, java.sql.Types.VARCHAR);
							} else {
								pstmt_tDBOutput_3.setString(4, Produit.ID_Categorie);
							}

							if (Produit.ID_Bois == null) {
								pstmt_tDBOutput_3.setNull(5, java.sql.Types.VARCHAR);
							} else {
								pstmt_tDBOutput_3.setString(5, Produit.ID_Bois);
							}

							if (Produit.Longueur == null) {
								pstmt_tDBOutput_3.setNull(6, java.sql.Types.VARCHAR);
							} else {
								pstmt_tDBOutput_3.setString(6, Produit.Longueur);
							}

							if (Produit.Largeur == null) {
								pstmt_tDBOutput_3.setNull(7, java.sql.Types.VARCHAR);
							} else {
								pstmt_tDBOutput_3.setString(7, Produit.Largeur);
							}

							if (Produit.Couleur == null) {
								pstmt_tDBOutput_3.setNull(8, java.sql.Types.VARCHAR);
							} else {
								pstmt_tDBOutput_3.setString(8, Produit.Couleur);
							}

							if (Produit.Stock_Disponib == null) {
								pstmt_tDBOutput_3.setNull(9, java.sql.Types.VARCHAR);
							} else {
								pstmt_tDBOutput_3.setString(9, Produit.Stock_Disponib);
							}

							if (Produit.Date_Ajout == null) {
								pstmt_tDBOutput_3.setNull(10, java.sql.Types.VARCHAR);
							} else {
								pstmt_tDBOutput_3.setString(10, Produit.Date_Ajout);
							}

							pstmt_tDBOutput_3.addBatch();
							nb_line_tDBOutput_3++;

							batchSizeCounter_tDBOutput_3++;
							if (batchSize_tDBOutput_3 <= batchSizeCounter_tDBOutput_3) {
								try {
									int countSum_tDBOutput_3 = 0;
									for (int countEach_tDBOutput_3 : pstmt_tDBOutput_3.executeBatch()) {
										countSum_tDBOutput_3 += (countEach_tDBOutput_3 == java.sql.Statement.EXECUTE_FAILED
												? 0
												: 1);
									}
									rowsToCommitCount_tDBOutput_3 += countSum_tDBOutput_3;
									insertedCount_tDBOutput_3 += countSum_tDBOutput_3;
								} catch (java.sql.BatchUpdateException e) {
									globalMap.put("tDBOutput_3_ERROR_MESSAGE", e.getMessage());
									int countSum_tDBOutput_3 = 0;
									for (int countEach_tDBOutput_3 : e.getUpdateCounts()) {
										countSum_tDBOutput_3 += (countEach_tDBOutput_3 < 0 ? 0 : countEach_tDBOutput_3);
									}
									rowsToCommitCount_tDBOutput_3 += countSum_tDBOutput_3;
									insertedCount_tDBOutput_3 += countSum_tDBOutput_3;
									System.err.println(e.getMessage());
								}

								batchSizeCounter_tDBOutput_3 = 0;
							}
							commitCounter_tDBOutput_3++;

							if (commitEvery_tDBOutput_3 <= commitCounter_tDBOutput_3) {

								try {
									int countSum_tDBOutput_3 = 0;
									for (int countEach_tDBOutput_3 : pstmt_tDBOutput_3.executeBatch()) {
										countSum_tDBOutput_3 += (countEach_tDBOutput_3 < 0 ? 0 : 1);
									}
									rowsToCommitCount_tDBOutput_3 += countSum_tDBOutput_3;
									insertedCount_tDBOutput_3 += countSum_tDBOutput_3;
								} catch (java.sql.BatchUpdateException e) {
									globalMap.put("tDBOutput_3_ERROR_MESSAGE", e.getMessage());
									int countSum_tDBOutput_3 = 0;
									for (int countEach_tDBOutput_3 : e.getUpdateCounts()) {
										countSum_tDBOutput_3 += (countEach_tDBOutput_3 < 0 ? 0 : countEach_tDBOutput_3);
									}
									rowsToCommitCount_tDBOutput_3 += countSum_tDBOutput_3;
									insertedCount_tDBOutput_3 += countSum_tDBOutput_3;
									System.err.println(e.getMessage());

								}
								if (rowsToCommitCount_tDBOutput_3 != 0) {
								}
								conn_tDBOutput_3.commit();
								if (rowsToCommitCount_tDBOutput_3 != 0) {
									rowsToCommitCount_tDBOutput_3 = 0;
								}
								commitCounter_tDBOutput_3 = 0;

							}

							tos_count_tDBOutput_3++;

							/**
							 * [tDBOutput_3 main ] stop
							 */

							/**
							 * [tDBOutput_3 process_data_begin ] start
							 */

							currentComponent = "tDBOutput_3";

							/**
							 * [tDBOutput_3 process_data_begin ] stop
							 */

							/**
							 * [tDBOutput_3 process_data_end ] start
							 */

							currentComponent = "tDBOutput_3";

							/**
							 * [tDBOutput_3 process_data_end ] stop
							 */

						} // End of branch "Produit"

						/**
						 * [tMap_4 process_data_end ] start
						 */

						currentComponent = "tMap_4";

						/**
						 * [tMap_4 process_data_end ] stop
						 */

						/**
						 * [tDBInput_3 process_data_end ] start
						 */

						currentComponent = "tDBInput_3";

						/**
						 * [tDBInput_3 process_data_end ] stop
						 */

						/**
						 * [tDBInput_3 end ] start
						 */

						currentComponent = "tDBInput_3";

					}
				} finally {
					if (rs_tDBInput_3 != null) {
						rs_tDBInput_3.close();
					}
					if (stmt_tDBInput_3 != null) {
						stmt_tDBInput_3.close();
					}
					if (conn_tDBInput_3 != null && !conn_tDBInput_3.isClosed()) {

						conn_tDBInput_3.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}

				}

				globalMap.put("tDBInput_3_NB_LINE", nb_line_tDBInput_3);

				ok_Hash.put("tDBInput_3", true);
				end_Hash.put("tDBInput_3", System.currentTimeMillis());

				/**
				 * [tDBInput_3 end ] stop
				 */

				/**
				 * [tMap_4 end ] start
				 */

				currentComponent = "tMap_4";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row3");
				}

				ok_Hash.put("tMap_4", true);
				end_Hash.put("tMap_4", System.currentTimeMillis());

				/**
				 * [tMap_4 end ] stop
				 */

				/**
				 * [tDBOutput_3 end ] start
				 */

				currentComponent = "tDBOutput_3";

				try {
					if (batchSizeCounter_tDBOutput_3 != 0) {
						int countSum_tDBOutput_3 = 0;

						for (int countEach_tDBOutput_3 : pstmt_tDBOutput_3.executeBatch()) {
							countSum_tDBOutput_3 += (countEach_tDBOutput_3 == java.sql.Statement.EXECUTE_FAILED ? 0
									: 1);
						}
						rowsToCommitCount_tDBOutput_3 += countSum_tDBOutput_3;

						insertedCount_tDBOutput_3 += countSum_tDBOutput_3;

					}

				} catch (java.sql.BatchUpdateException e) {
					globalMap.put(currentComponent + "_ERROR_MESSAGE", e.getMessage());

					int countSum_tDBOutput_3 = 0;
					for (int countEach_tDBOutput_3 : e.getUpdateCounts()) {
						countSum_tDBOutput_3 += (countEach_tDBOutput_3 < 0 ? 0 : countEach_tDBOutput_3);
					}
					rowsToCommitCount_tDBOutput_3 += countSum_tDBOutput_3;

					insertedCount_tDBOutput_3 += countSum_tDBOutput_3;

					System.err.println(e.getMessage());

				}
				batchSizeCounter_tDBOutput_3 = 0;

				if (pstmt_tDBOutput_3 != null) {

					pstmt_tDBOutput_3.close();
					resourceMap.remove("pstmt_tDBOutput_3");

				}
				resourceMap.put("statementClosed_tDBOutput_3", true);
				if (commitCounter_tDBOutput_3 > 0 && rowsToCommitCount_tDBOutput_3 != 0) {

				}
				conn_tDBOutput_3.commit();
				if (commitCounter_tDBOutput_3 > 0 && rowsToCommitCount_tDBOutput_3 != 0) {

					rowsToCommitCount_tDBOutput_3 = 0;
				}
				commitCounter_tDBOutput_3 = 0;

				conn_tDBOutput_3.close();

				resourceMap.put("finish_tDBOutput_3", true);

				nb_line_deleted_tDBOutput_3 = nb_line_deleted_tDBOutput_3 + deletedCount_tDBOutput_3;
				nb_line_update_tDBOutput_3 = nb_line_update_tDBOutput_3 + updatedCount_tDBOutput_3;
				nb_line_inserted_tDBOutput_3 = nb_line_inserted_tDBOutput_3 + insertedCount_tDBOutput_3;
				nb_line_rejected_tDBOutput_3 = nb_line_rejected_tDBOutput_3 + rejectedCount_tDBOutput_3;

				globalMap.put("tDBOutput_3_NB_LINE", nb_line_tDBOutput_3);
				globalMap.put("tDBOutput_3_NB_LINE_UPDATED", nb_line_update_tDBOutput_3);
				globalMap.put("tDBOutput_3_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_3);
				globalMap.put("tDBOutput_3_NB_LINE_DELETED", nb_line_deleted_tDBOutput_3);
				globalMap.put("tDBOutput_3_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_3);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "Produit");
				}

				ok_Hash.put("tDBOutput_3", true);
				end_Hash.put("tDBOutput_3", System.currentTimeMillis());

				/**
				 * [tDBOutput_3 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBInput_3 finally ] start
				 */

				currentComponent = "tDBInput_3";

				/**
				 * [tDBInput_3 finally ] stop
				 */

				/**
				 * [tMap_4 finally ] start
				 */

				currentComponent = "tMap_4";

				/**
				 * [tMap_4 finally ] stop
				 */

				/**
				 * [tDBOutput_3 finally ] start
				 */

				currentComponent = "tDBOutput_3";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_3") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_3 = null;
						if ((pstmtToClose_tDBOutput_3 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_3")) != null) {
							pstmtToClose_tDBOutput_3.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_3") == null) {
						java.sql.Connection ctn_tDBOutput_3 = null;
						if ((ctn_tDBOutput_3 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_3")) != null) {
							try {
								ctn_tDBOutput_3.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_3) {
								String errorMessage_tDBOutput_3 = "failed to close the connection in tDBOutput_3 :"
										+ sqlEx_tDBOutput_3.getMessage();
								System.err.println(errorMessage_tDBOutput_3);
							}
						}
					}
				}

				/**
				 * [tDBOutput_3 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_3_SUBPROCESS_STATE", 1);
	}

	public static class ClientStruct implements routines.system.IPersistableRow<ClientStruct> {
		final static byte[] commonByteArrayLock_LOCAL_PROJECT_Dim_temp = new byte[0];
		static byte[] commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int ID_Client;

		public int getID_Client() {
			return this.ID_Client;
		}

		public String first_name;

		public String getFirst_name() {
			return this.first_name;
		}

		public String last_name;

		public String getLast_name() {
			return this.last_name;
		}

		public String phone;

		public String getPhone() {
			return this.phone;
		}

		public String gender;

		public String getGender() {
			return this.gender;
		}

		public String address;

		public String getAddress() {
			return this.address;
		}

		public String city;

		public String getCity() {
			return this.city;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + (int) this.ID_Client;

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final ClientStruct other = (ClientStruct) obj;

			if (this.ID_Client != other.ID_Client)
				return false;

			return true;
		}

		public void copyDataTo(ClientStruct other) {

			other.ID_Client = this.ID_Client;
			other.first_name = this.first_name;
			other.last_name = this.last_name;
			other.phone = this.phone;
			other.gender = this.gender;
			other.address = this.address;
			other.city = this.city;

		}

		public void copyKeysDataTo(ClientStruct other) {

			other.ID_Client = this.ID_Client;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_LOCAL_PROJECT_Dim_temp.length) {
					if (length < 1024 && commonByteArray_LOCAL_PROJECT_Dim_temp.length == 0) {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[1024];
					} else {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length);
				strReturn = new String(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_LOCAL_PROJECT_Dim_temp.length) {
					if (length < 1024 && commonByteArray_LOCAL_PROJECT_Dim_temp.length == 0) {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[1024];
					} else {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length);
				strReturn = new String(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_LOCAL_PROJECT_Dim_temp) {

				try {

					int length = 0;

					this.ID_Client = dis.readInt();

					this.first_name = readString(dis);

					this.last_name = readString(dis);

					this.phone = readString(dis);

					this.gender = readString(dis);

					this.address = readString(dis);

					this.city = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_LOCAL_PROJECT_Dim_temp) {

				try {

					int length = 0;

					this.ID_Client = dis.readInt();

					this.first_name = readString(dis);

					this.last_name = readString(dis);

					this.phone = readString(dis);

					this.gender = readString(dis);

					this.address = readString(dis);

					this.city = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.ID_Client);

				// String

				writeString(this.first_name, dos);

				// String

				writeString(this.last_name, dos);

				// String

				writeString(this.phone, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.address, dos);

				// String

				writeString(this.city, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.ID_Client);

				// String

				writeString(this.first_name, dos);

				// String

				writeString(this.last_name, dos);

				// String

				writeString(this.phone, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.address, dos);

				// String

				writeString(this.city, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID_Client=" + String.valueOf(ID_Client));
			sb.append(",first_name=" + first_name);
			sb.append(",last_name=" + last_name);
			sb.append(",phone=" + phone);
			sb.append(",gender=" + gender);
			sb.append(",address=" + address);
			sb.append(",city=" + city);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(ClientStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ID_Client, other.ID_Client);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_LOCAL_PROJECT_Dim_temp = new byte[0];
		static byte[] commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[0];

		public int ID_Client;

		public int getID_Client() {
			return this.ID_Client;
		}

		public String first_name;

		public String getFirst_name() {
			return this.first_name;
		}

		public String last_name;

		public String getLast_name() {
			return this.last_name;
		}

		public String phone;

		public String getPhone() {
			return this.phone;
		}

		public String gender;

		public String getGender() {
			return this.gender;
		}

		public String address;

		public String getAddress() {
			return this.address;
		}

		public String city;

		public String getCity() {
			return this.city;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_LOCAL_PROJECT_Dim_temp.length) {
					if (length < 1024 && commonByteArray_LOCAL_PROJECT_Dim_temp.length == 0) {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[1024];
					} else {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length);
				strReturn = new String(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_LOCAL_PROJECT_Dim_temp.length) {
					if (length < 1024 && commonByteArray_LOCAL_PROJECT_Dim_temp.length == 0) {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[1024];
					} else {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length);
				strReturn = new String(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_LOCAL_PROJECT_Dim_temp) {

				try {

					int length = 0;

					this.ID_Client = dis.readInt();

					this.first_name = readString(dis);

					this.last_name = readString(dis);

					this.phone = readString(dis);

					this.gender = readString(dis);

					this.address = readString(dis);

					this.city = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_LOCAL_PROJECT_Dim_temp) {

				try {

					int length = 0;

					this.ID_Client = dis.readInt();

					this.first_name = readString(dis);

					this.last_name = readString(dis);

					this.phone = readString(dis);

					this.gender = readString(dis);

					this.address = readString(dis);

					this.city = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.ID_Client);

				// String

				writeString(this.first_name, dos);

				// String

				writeString(this.last_name, dos);

				// String

				writeString(this.phone, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.address, dos);

				// String

				writeString(this.city, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.ID_Client);

				// String

				writeString(this.first_name, dos);

				// String

				writeString(this.last_name, dos);

				// String

				writeString(this.phone, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.address, dos);

				// String

				writeString(this.city, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID_Client=" + String.valueOf(ID_Client));
			sb.append(",first_name=" + first_name);
			sb.append(",last_name=" + last_name);
			sb.append(",phone=" + phone);
			sb.append(",gender=" + gender);
			sb.append(",address=" + address);
			sb.append(",city=" + city);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_4Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_4_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row2Struct row2 = new row2Struct();
				ClientStruct Client = new ClientStruct();

				/**
				 * [tDBOutput_2 begin ] start
				 */

				ok_Hash.put("tDBOutput_2", false);
				start_Hash.put("tDBOutput_2", System.currentTimeMillis());

				currentComponent = "tDBOutput_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "Client");
				}

				int tos_count_tDBOutput_2 = 0;

				int nb_line_tDBOutput_2 = 0;
				int nb_line_update_tDBOutput_2 = 0;
				int nb_line_inserted_tDBOutput_2 = 0;
				int nb_line_deleted_tDBOutput_2 = 0;
				int nb_line_rejected_tDBOutput_2 = 0;

				int deletedCount_tDBOutput_2 = 0;
				int updatedCount_tDBOutput_2 = 0;
				int insertedCount_tDBOutput_2 = 0;
				int rowsToCommitCount_tDBOutput_2 = 0;
				int rejectedCount_tDBOutput_2 = 0;

				String tableName_tDBOutput_2 = "Dim_Client";
				boolean whetherReject_tDBOutput_2 = false;

				java.util.Calendar calendar_tDBOutput_2 = java.util.Calendar.getInstance();
				calendar_tDBOutput_2.set(1, 0, 1, 0, 0, 0);
				long year1_tDBOutput_2 = calendar_tDBOutput_2.getTime().getTime();
				calendar_tDBOutput_2.set(10000, 0, 1, 0, 0, 0);
				long year10000_tDBOutput_2 = calendar_tDBOutput_2.getTime().getTime();
				long date_tDBOutput_2;

				java.sql.Connection conn_tDBOutput_2 = null;

				String properties_tDBOutput_2 = "noDatetimeStringSync=true";
				if (properties_tDBOutput_2 == null || properties_tDBOutput_2.trim().length() == 0) {
					properties_tDBOutput_2 = "rewriteBatchedStatements=true&allowLoadLocalInfile=true";
				} else {
					if (!properties_tDBOutput_2.contains("rewriteBatchedStatements=")) {
						properties_tDBOutput_2 += "&rewriteBatchedStatements=true";
					}

					if (!properties_tDBOutput_2.contains("allowLoadLocalInfile=")) {
						properties_tDBOutput_2 += "&allowLoadLocalInfile=true";
					}
				}

				String url_tDBOutput_2 = "jdbc:mysql://" + "127.0.0.1" + ":" + "3306" + "/" + "Cube_olap" + "?"
						+ properties_tDBOutput_2;

				String driverClass_tDBOutput_2 = "com.mysql.jdbc.Driver";

				String dbUser_tDBOutput_2 = "root";

				final String decryptedPassword_tDBOutput_2 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:T5SO+/d2eCrEmAbYYISl1SWdM3Fud27aJf8uEOMMJpHbPRoXDY6IE58T6kw=");

				String dbPwd_tDBOutput_2 = decryptedPassword_tDBOutput_2;
				java.lang.Class.forName(driverClass_tDBOutput_2);

				conn_tDBOutput_2 = java.sql.DriverManager.getConnection(url_tDBOutput_2, dbUser_tDBOutput_2,
						dbPwd_tDBOutput_2);

				resourceMap.put("conn_tDBOutput_2", conn_tDBOutput_2);
				conn_tDBOutput_2.setAutoCommit(false);
				int commitEvery_tDBOutput_2 = 10000;
				int commitCounter_tDBOutput_2 = 0;

				int count_tDBOutput_2 = 0;

				java.sql.DatabaseMetaData dbMetaData_tDBOutput_2 = conn_tDBOutput_2.getMetaData();
				java.sql.ResultSet rsTable_tDBOutput_2 = dbMetaData_tDBOutput_2.getTables("Cube_olap", null, null,
						new String[] { "TABLE" });
				boolean whetherExist_tDBOutput_2 = false;
				while (rsTable_tDBOutput_2.next()) {
					String table_tDBOutput_2 = rsTable_tDBOutput_2.getString("TABLE_NAME");
					if (table_tDBOutput_2.equalsIgnoreCase("Dim_Client")) {
						whetherExist_tDBOutput_2 = true;
						break;
					}
				}
				if (whetherExist_tDBOutput_2) {
					try (java.sql.Statement stmtDrop_tDBOutput_2 = conn_tDBOutput_2.createStatement()) {
						stmtDrop_tDBOutput_2.execute("DROP TABLE `" + tableName_tDBOutput_2 + "`");
					}
				}
				try (java.sql.Statement stmtCreate_tDBOutput_2 = conn_tDBOutput_2.createStatement()) {
					stmtCreate_tDBOutput_2.execute("CREATE TABLE `" + tableName_tDBOutput_2
							+ "`(`ID_Client` INT(10)   not null ,`first_name` VARCHAR(999)  ,`last_name` VARCHAR(999)  ,`phone` VARCHAR(999)  ,`gender` VARCHAR(999)  ,`address` VARCHAR(999)  ,`city` VARCHAR(999)  ,primary key(`ID_Client`))");
				}

				String insert_tDBOutput_2 = "INSERT INTO `" + "Dim_Client"
						+ "` (`ID_Client`,`first_name`,`last_name`,`phone`,`gender`,`address`,`city`) VALUES (?,?,?,?,?,?,?)";
				int batchSize_tDBOutput_2 = 100;
				int batchSizeCounter_tDBOutput_2 = 0;

				java.sql.PreparedStatement pstmt_tDBOutput_2 = conn_tDBOutput_2.prepareStatement(insert_tDBOutput_2);
				resourceMap.put("pstmt_tDBOutput_2", pstmt_tDBOutput_2);

				/**
				 * [tDBOutput_2 begin ] stop
				 */

				/**
				 * [tMap_3 begin ] start
				 */

				ok_Hash.put("tMap_3", false);
				start_Hash.put("tMap_3", System.currentTimeMillis());

				currentComponent = "tMap_3";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row2");
				}

				int tos_count_tMap_3 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_3__Struct {
				}
				Var__tMap_3__Struct Var__tMap_3 = new Var__tMap_3__Struct();
// ###############################

// ###############################
// # Outputs initialization
				ClientStruct Client_tmp = new ClientStruct();
// ###############################

				/**
				 * [tMap_3 begin ] stop
				 */

				/**
				 * [tDBInput_4 begin ] start
				 */

				ok_Hash.put("tDBInput_4", false);
				start_Hash.put("tDBInput_4", System.currentTimeMillis());

				currentComponent = "tDBInput_4";

				int tos_count_tDBInput_4 = 0;

				java.util.Calendar calendar_tDBInput_4 = java.util.Calendar.getInstance();
				calendar_tDBInput_4.set(0, 0, 0, 0, 0, 0);
				java.util.Date year0_tDBInput_4 = calendar_tDBInput_4.getTime();
				int nb_line_tDBInput_4 = 0;
				java.sql.Connection conn_tDBInput_4 = null;
				String driverClass_tDBInput_4 = "com.mysql.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_4 = java.lang.Class.forName(driverClass_tDBInput_4);
				String dbUser_tDBInput_4 = "root";

				final String decryptedPassword_tDBInput_4 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:Fz1dyL684eh5qcZrIbmvq5nbf8dLNYMYmU0P9cJeliuZN291LtxJmdnoMs8=");

				String dbPwd_tDBInput_4 = decryptedPassword_tDBInput_4;

				String properties_tDBInput_4 = "noDatetimeStringSync=true";
				if (properties_tDBInput_4 == null || properties_tDBInput_4.trim().length() == 0) {
					properties_tDBInput_4 = "";
				}
				String url_tDBInput_4 = "jdbc:mysql://" + "127.0.0.1" + ":" + "3306" + "/" + "data_warehouse_vente"
						+ "?" + properties_tDBInput_4;

				conn_tDBInput_4 = java.sql.DriverManager.getConnection(url_tDBInput_4, dbUser_tDBInput_4,
						dbPwd_tDBInput_4);

				java.sql.Statement stmt_tDBInput_4 = conn_tDBInput_4.createStatement();

				String dbquery_tDBInput_4 = "SELECT \n  `client`.`ID_Client`, \n  `client`.`first_name`, \n  `client`.`last_name`, \n  `client`.`phone`, \n  `client`.`ge"
						+ "nder`, \n  `client`.`address`, \n  `client`.`city`\nFROM `client`";

				globalMap.put("tDBInput_4_QUERY", dbquery_tDBInput_4);
				java.sql.ResultSet rs_tDBInput_4 = null;

				try {
					rs_tDBInput_4 = stmt_tDBInput_4.executeQuery(dbquery_tDBInput_4);
					java.sql.ResultSetMetaData rsmd_tDBInput_4 = rs_tDBInput_4.getMetaData();
					int colQtyInRs_tDBInput_4 = rsmd_tDBInput_4.getColumnCount();

					String tmpContent_tDBInput_4 = null;

					while (rs_tDBInput_4.next()) {
						nb_line_tDBInput_4++;

						if (colQtyInRs_tDBInput_4 < 1) {
							row2.ID_Client = 0;
						} else {

							row2.ID_Client = rs_tDBInput_4.getInt(1);
							if (rs_tDBInput_4.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_4 < 2) {
							row2.first_name = null;
						} else {

							row2.first_name = routines.system.JDBCUtil.getString(rs_tDBInput_4, 2, false);
						}
						if (colQtyInRs_tDBInput_4 < 3) {
							row2.last_name = null;
						} else {

							row2.last_name = routines.system.JDBCUtil.getString(rs_tDBInput_4, 3, false);
						}
						if (colQtyInRs_tDBInput_4 < 4) {
							row2.phone = null;
						} else {

							row2.phone = routines.system.JDBCUtil.getString(rs_tDBInput_4, 4, false);
						}
						if (colQtyInRs_tDBInput_4 < 5) {
							row2.gender = null;
						} else {

							row2.gender = routines.system.JDBCUtil.getString(rs_tDBInput_4, 5, false);
						}
						if (colQtyInRs_tDBInput_4 < 6) {
							row2.address = null;
						} else {

							row2.address = routines.system.JDBCUtil.getString(rs_tDBInput_4, 6, false);
						}
						if (colQtyInRs_tDBInput_4 < 7) {
							row2.city = null;
						} else {

							row2.city = routines.system.JDBCUtil.getString(rs_tDBInput_4, 7, false);
						}

						/**
						 * [tDBInput_4 begin ] stop
						 */

						/**
						 * [tDBInput_4 main ] start
						 */

						currentComponent = "tDBInput_4";

						tos_count_tDBInput_4++;

						/**
						 * [tDBInput_4 main ] stop
						 */

						/**
						 * [tDBInput_4 process_data_begin ] start
						 */

						currentComponent = "tDBInput_4";

						/**
						 * [tDBInput_4 process_data_begin ] stop
						 */

						/**
						 * [tMap_3 main ] start
						 */

						currentComponent = "tMap_3";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row2"

							);
						}

						boolean hasCasePrimitiveKeyWithNull_tMap_3 = false;

						// ###############################
						// # Input tables (lookups)
						boolean rejectedInnerJoin_tMap_3 = false;
						boolean mainRowRejected_tMap_3 = false;

						// ###############################
						{ // start of Var scope

							// ###############################
							// # Vars tables

							Var__tMap_3__Struct Var = Var__tMap_3;// ###############################
							// ###############################
							// # Output tables

							Client = null;

// # Output table : 'Client'
							Client_tmp.ID_Client = row2.ID_Client;
							Client_tmp.first_name = row2.first_name;
							Client_tmp.last_name = row2.last_name;
							Client_tmp.phone = row2.phone;
							Client_tmp.gender = row2.gender;
							Client_tmp.address = row2.address;
							Client_tmp.city = row2.city;
							Client = Client_tmp;
// ###############################

						} // end of Var scope

						rejectedInnerJoin_tMap_3 = false;

						tos_count_tMap_3++;

						/**
						 * [tMap_3 main ] stop
						 */

						/**
						 * [tMap_3 process_data_begin ] start
						 */

						currentComponent = "tMap_3";

						/**
						 * [tMap_3 process_data_begin ] stop
						 */
// Start of branch "Client"
						if (Client != null) {

							/**
							 * [tDBOutput_2 main ] start
							 */

							currentComponent = "tDBOutput_2";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "Client"

								);
							}

							whetherReject_tDBOutput_2 = false;
							pstmt_tDBOutput_2.setInt(1, Client.ID_Client);

							if (Client.first_name == null) {
								pstmt_tDBOutput_2.setNull(2, java.sql.Types.VARCHAR);
							} else {
								pstmt_tDBOutput_2.setString(2, Client.first_name);
							}

							if (Client.last_name == null) {
								pstmt_tDBOutput_2.setNull(3, java.sql.Types.VARCHAR);
							} else {
								pstmt_tDBOutput_2.setString(3, Client.last_name);
							}

							if (Client.phone == null) {
								pstmt_tDBOutput_2.setNull(4, java.sql.Types.VARCHAR);
							} else {
								pstmt_tDBOutput_2.setString(4, Client.phone);
							}

							if (Client.gender == null) {
								pstmt_tDBOutput_2.setNull(5, java.sql.Types.VARCHAR);
							} else {
								pstmt_tDBOutput_2.setString(5, Client.gender);
							}

							if (Client.address == null) {
								pstmt_tDBOutput_2.setNull(6, java.sql.Types.VARCHAR);
							} else {
								pstmt_tDBOutput_2.setString(6, Client.address);
							}

							if (Client.city == null) {
								pstmt_tDBOutput_2.setNull(7, java.sql.Types.VARCHAR);
							} else {
								pstmt_tDBOutput_2.setString(7, Client.city);
							}

							pstmt_tDBOutput_2.addBatch();
							nb_line_tDBOutput_2++;

							batchSizeCounter_tDBOutput_2++;
							if (batchSize_tDBOutput_2 <= batchSizeCounter_tDBOutput_2) {
								try {
									int countSum_tDBOutput_2 = 0;
									for (int countEach_tDBOutput_2 : pstmt_tDBOutput_2.executeBatch()) {
										countSum_tDBOutput_2 += (countEach_tDBOutput_2 == java.sql.Statement.EXECUTE_FAILED
												? 0
												: 1);
									}
									rowsToCommitCount_tDBOutput_2 += countSum_tDBOutput_2;
									insertedCount_tDBOutput_2 += countSum_tDBOutput_2;
								} catch (java.sql.BatchUpdateException e) {
									globalMap.put("tDBOutput_2_ERROR_MESSAGE", e.getMessage());
									int countSum_tDBOutput_2 = 0;
									for (int countEach_tDBOutput_2 : e.getUpdateCounts()) {
										countSum_tDBOutput_2 += (countEach_tDBOutput_2 < 0 ? 0 : countEach_tDBOutput_2);
									}
									rowsToCommitCount_tDBOutput_2 += countSum_tDBOutput_2;
									insertedCount_tDBOutput_2 += countSum_tDBOutput_2;
									System.err.println(e.getMessage());
								}

								batchSizeCounter_tDBOutput_2 = 0;
							}
							commitCounter_tDBOutput_2++;

							if (commitEvery_tDBOutput_2 <= commitCounter_tDBOutput_2) {

								try {
									int countSum_tDBOutput_2 = 0;
									for (int countEach_tDBOutput_2 : pstmt_tDBOutput_2.executeBatch()) {
										countSum_tDBOutput_2 += (countEach_tDBOutput_2 < 0 ? 0 : 1);
									}
									rowsToCommitCount_tDBOutput_2 += countSum_tDBOutput_2;
									insertedCount_tDBOutput_2 += countSum_tDBOutput_2;
								} catch (java.sql.BatchUpdateException e) {
									globalMap.put("tDBOutput_2_ERROR_MESSAGE", e.getMessage());
									int countSum_tDBOutput_2 = 0;
									for (int countEach_tDBOutput_2 : e.getUpdateCounts()) {
										countSum_tDBOutput_2 += (countEach_tDBOutput_2 < 0 ? 0 : countEach_tDBOutput_2);
									}
									rowsToCommitCount_tDBOutput_2 += countSum_tDBOutput_2;
									insertedCount_tDBOutput_2 += countSum_tDBOutput_2;
									System.err.println(e.getMessage());

								}
								if (rowsToCommitCount_tDBOutput_2 != 0) {
								}
								conn_tDBOutput_2.commit();
								if (rowsToCommitCount_tDBOutput_2 != 0) {
									rowsToCommitCount_tDBOutput_2 = 0;
								}
								commitCounter_tDBOutput_2 = 0;

							}

							tos_count_tDBOutput_2++;

							/**
							 * [tDBOutput_2 main ] stop
							 */

							/**
							 * [tDBOutput_2 process_data_begin ] start
							 */

							currentComponent = "tDBOutput_2";

							/**
							 * [tDBOutput_2 process_data_begin ] stop
							 */

							/**
							 * [tDBOutput_2 process_data_end ] start
							 */

							currentComponent = "tDBOutput_2";

							/**
							 * [tDBOutput_2 process_data_end ] stop
							 */

						} // End of branch "Client"

						/**
						 * [tMap_3 process_data_end ] start
						 */

						currentComponent = "tMap_3";

						/**
						 * [tMap_3 process_data_end ] stop
						 */

						/**
						 * [tDBInput_4 process_data_end ] start
						 */

						currentComponent = "tDBInput_4";

						/**
						 * [tDBInput_4 process_data_end ] stop
						 */

						/**
						 * [tDBInput_4 end ] start
						 */

						currentComponent = "tDBInput_4";

					}
				} finally {
					if (rs_tDBInput_4 != null) {
						rs_tDBInput_4.close();
					}
					if (stmt_tDBInput_4 != null) {
						stmt_tDBInput_4.close();
					}
					if (conn_tDBInput_4 != null && !conn_tDBInput_4.isClosed()) {

						conn_tDBInput_4.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}

				}

				globalMap.put("tDBInput_4_NB_LINE", nb_line_tDBInput_4);

				ok_Hash.put("tDBInput_4", true);
				end_Hash.put("tDBInput_4", System.currentTimeMillis());

				/**
				 * [tDBInput_4 end ] stop
				 */

				/**
				 * [tMap_3 end ] start
				 */

				currentComponent = "tMap_3";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row2");
				}

				ok_Hash.put("tMap_3", true);
				end_Hash.put("tMap_3", System.currentTimeMillis());

				/**
				 * [tMap_3 end ] stop
				 */

				/**
				 * [tDBOutput_2 end ] start
				 */

				currentComponent = "tDBOutput_2";

				try {
					if (batchSizeCounter_tDBOutput_2 != 0) {
						int countSum_tDBOutput_2 = 0;

						for (int countEach_tDBOutput_2 : pstmt_tDBOutput_2.executeBatch()) {
							countSum_tDBOutput_2 += (countEach_tDBOutput_2 == java.sql.Statement.EXECUTE_FAILED ? 0
									: 1);
						}
						rowsToCommitCount_tDBOutput_2 += countSum_tDBOutput_2;

						insertedCount_tDBOutput_2 += countSum_tDBOutput_2;

					}

				} catch (java.sql.BatchUpdateException e) {
					globalMap.put(currentComponent + "_ERROR_MESSAGE", e.getMessage());

					int countSum_tDBOutput_2 = 0;
					for (int countEach_tDBOutput_2 : e.getUpdateCounts()) {
						countSum_tDBOutput_2 += (countEach_tDBOutput_2 < 0 ? 0 : countEach_tDBOutput_2);
					}
					rowsToCommitCount_tDBOutput_2 += countSum_tDBOutput_2;

					insertedCount_tDBOutput_2 += countSum_tDBOutput_2;

					System.err.println(e.getMessage());

				}
				batchSizeCounter_tDBOutput_2 = 0;

				if (pstmt_tDBOutput_2 != null) {

					pstmt_tDBOutput_2.close();
					resourceMap.remove("pstmt_tDBOutput_2");

				}
				resourceMap.put("statementClosed_tDBOutput_2", true);
				if (commitCounter_tDBOutput_2 > 0 && rowsToCommitCount_tDBOutput_2 != 0) {

				}
				conn_tDBOutput_2.commit();
				if (commitCounter_tDBOutput_2 > 0 && rowsToCommitCount_tDBOutput_2 != 0) {

					rowsToCommitCount_tDBOutput_2 = 0;
				}
				commitCounter_tDBOutput_2 = 0;

				conn_tDBOutput_2.close();

				resourceMap.put("finish_tDBOutput_2", true);

				nb_line_deleted_tDBOutput_2 = nb_line_deleted_tDBOutput_2 + deletedCount_tDBOutput_2;
				nb_line_update_tDBOutput_2 = nb_line_update_tDBOutput_2 + updatedCount_tDBOutput_2;
				nb_line_inserted_tDBOutput_2 = nb_line_inserted_tDBOutput_2 + insertedCount_tDBOutput_2;
				nb_line_rejected_tDBOutput_2 = nb_line_rejected_tDBOutput_2 + rejectedCount_tDBOutput_2;

				globalMap.put("tDBOutput_2_NB_LINE", nb_line_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_UPDATED", nb_line_update_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_DELETED", nb_line_deleted_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_2);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "Client");
				}

				ok_Hash.put("tDBOutput_2", true);
				end_Hash.put("tDBOutput_2", System.currentTimeMillis());

				/**
				 * [tDBOutput_2 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBInput_4 finally ] start
				 */

				currentComponent = "tDBInput_4";

				/**
				 * [tDBInput_4 finally ] stop
				 */

				/**
				 * [tMap_3 finally ] start
				 */

				currentComponent = "tMap_3";

				/**
				 * [tMap_3 finally ] stop
				 */

				/**
				 * [tDBOutput_2 finally ] start
				 */

				currentComponent = "tDBOutput_2";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_2") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_2 = null;
						if ((pstmtToClose_tDBOutput_2 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_2")) != null) {
							pstmtToClose_tDBOutput_2.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_2") == null) {
						java.sql.Connection ctn_tDBOutput_2 = null;
						if ((ctn_tDBOutput_2 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_2")) != null) {
							try {
								ctn_tDBOutput_2.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_2) {
								String errorMessage_tDBOutput_2 = "failed to close the connection in tDBOutput_2 :"
										+ sqlEx_tDBOutput_2.getMessage();
								System.err.println(errorMessage_tDBOutput_2);
							}
						}
					}
				}

				/**
				 * [tDBOutput_2 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_4_SUBPROCESS_STATE", 1);
	}

	public static class categorieStruct implements routines.system.IPersistableRow<categorieStruct> {
		final static byte[] commonByteArrayLock_LOCAL_PROJECT_Dim_temp = new byte[0];
		static byte[] commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int ID_Categorie;

		public int getID_Categorie() {
			return this.ID_Categorie;
		}

		public String Nom_Categorie;

		public String getNom_Categorie() {
			return this.Nom_Categorie;
		}

		public String Description;

		public String getDescription() {
			return this.Description;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + (int) this.ID_Categorie;

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final categorieStruct other = (categorieStruct) obj;

			if (this.ID_Categorie != other.ID_Categorie)
				return false;

			return true;
		}

		public void copyDataTo(categorieStruct other) {

			other.ID_Categorie = this.ID_Categorie;
			other.Nom_Categorie = this.Nom_Categorie;
			other.Description = this.Description;

		}

		public void copyKeysDataTo(categorieStruct other) {

			other.ID_Categorie = this.ID_Categorie;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_LOCAL_PROJECT_Dim_temp.length) {
					if (length < 1024 && commonByteArray_LOCAL_PROJECT_Dim_temp.length == 0) {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[1024];
					} else {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length);
				strReturn = new String(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_LOCAL_PROJECT_Dim_temp.length) {
					if (length < 1024 && commonByteArray_LOCAL_PROJECT_Dim_temp.length == 0) {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[1024];
					} else {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length);
				strReturn = new String(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_LOCAL_PROJECT_Dim_temp) {

				try {

					int length = 0;

					this.ID_Categorie = dis.readInt();

					this.Nom_Categorie = readString(dis);

					this.Description = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_LOCAL_PROJECT_Dim_temp) {

				try {

					int length = 0;

					this.ID_Categorie = dis.readInt();

					this.Nom_Categorie = readString(dis);

					this.Description = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.ID_Categorie);

				// String

				writeString(this.Nom_Categorie, dos);

				// String

				writeString(this.Description, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.ID_Categorie);

				// String

				writeString(this.Nom_Categorie, dos);

				// String

				writeString(this.Description, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID_Categorie=" + String.valueOf(ID_Categorie));
			sb.append(",Nom_Categorie=" + Nom_Categorie);
			sb.append(",Description=" + Description);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(categorieStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ID_Categorie, other.ID_Categorie);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row4Struct implements routines.system.IPersistableRow<row4Struct> {
		final static byte[] commonByteArrayLock_LOCAL_PROJECT_Dim_temp = new byte[0];
		static byte[] commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[0];

		public int ID_Categorie;

		public int getID_Categorie() {
			return this.ID_Categorie;
		}

		public String Nom_Categorie;

		public String getNom_Categorie() {
			return this.Nom_Categorie;
		}

		public String Description;

		public String getDescription() {
			return this.Description;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_LOCAL_PROJECT_Dim_temp.length) {
					if (length < 1024 && commonByteArray_LOCAL_PROJECT_Dim_temp.length == 0) {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[1024];
					} else {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length);
				strReturn = new String(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_LOCAL_PROJECT_Dim_temp.length) {
					if (length < 1024 && commonByteArray_LOCAL_PROJECT_Dim_temp.length == 0) {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[1024];
					} else {
						commonByteArray_LOCAL_PROJECT_Dim_temp = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length);
				strReturn = new String(commonByteArray_LOCAL_PROJECT_Dim_temp, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_LOCAL_PROJECT_Dim_temp) {

				try {

					int length = 0;

					this.ID_Categorie = dis.readInt();

					this.Nom_Categorie = readString(dis);

					this.Description = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_LOCAL_PROJECT_Dim_temp) {

				try {

					int length = 0;

					this.ID_Categorie = dis.readInt();

					this.Nom_Categorie = readString(dis);

					this.Description = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.ID_Categorie);

				// String

				writeString(this.Nom_Categorie, dos);

				// String

				writeString(this.Description, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.ID_Categorie);

				// String

				writeString(this.Nom_Categorie, dos);

				// String

				writeString(this.Description, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID_Categorie=" + String.valueOf(ID_Categorie));
			sb.append(",Nom_Categorie=" + Nom_Categorie);
			sb.append(",Description=" + Description);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row4Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_5Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_5_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row4Struct row4 = new row4Struct();
				categorieStruct categorie = new categorieStruct();

				/**
				 * [tDBOutput_4 begin ] start
				 */

				ok_Hash.put("tDBOutput_4", false);
				start_Hash.put("tDBOutput_4", System.currentTimeMillis());

				currentComponent = "tDBOutput_4";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "categorie");
				}

				int tos_count_tDBOutput_4 = 0;

				int nb_line_tDBOutput_4 = 0;
				int nb_line_update_tDBOutput_4 = 0;
				int nb_line_inserted_tDBOutput_4 = 0;
				int nb_line_deleted_tDBOutput_4 = 0;
				int nb_line_rejected_tDBOutput_4 = 0;

				int deletedCount_tDBOutput_4 = 0;
				int updatedCount_tDBOutput_4 = 0;
				int insertedCount_tDBOutput_4 = 0;
				int rowsToCommitCount_tDBOutput_4 = 0;
				int rejectedCount_tDBOutput_4 = 0;

				String tableName_tDBOutput_4 = "Dim_Categorie";
				boolean whetherReject_tDBOutput_4 = false;

				java.util.Calendar calendar_tDBOutput_4 = java.util.Calendar.getInstance();
				calendar_tDBOutput_4.set(1, 0, 1, 0, 0, 0);
				long year1_tDBOutput_4 = calendar_tDBOutput_4.getTime().getTime();
				calendar_tDBOutput_4.set(10000, 0, 1, 0, 0, 0);
				long year10000_tDBOutput_4 = calendar_tDBOutput_4.getTime().getTime();
				long date_tDBOutput_4;

				java.sql.Connection conn_tDBOutput_4 = null;

				String properties_tDBOutput_4 = "noDatetimeStringSync=true";
				if (properties_tDBOutput_4 == null || properties_tDBOutput_4.trim().length() == 0) {
					properties_tDBOutput_4 = "rewriteBatchedStatements=true&allowLoadLocalInfile=true";
				} else {
					if (!properties_tDBOutput_4.contains("rewriteBatchedStatements=")) {
						properties_tDBOutput_4 += "&rewriteBatchedStatements=true";
					}

					if (!properties_tDBOutput_4.contains("allowLoadLocalInfile=")) {
						properties_tDBOutput_4 += "&allowLoadLocalInfile=true";
					}
				}

				String url_tDBOutput_4 = "jdbc:mysql://" + "127.0.0.1" + ":" + "3306" + "/" + "Cube_olap" + "?"
						+ properties_tDBOutput_4;

				String driverClass_tDBOutput_4 = "com.mysql.jdbc.Driver";

				String dbUser_tDBOutput_4 = "root";

				final String decryptedPassword_tDBOutput_4 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:6tzW5D4ei6LL8+/ePnt5BXeLtBrC0S0vDW++mpmJVrADO0iTHfRs13k9hxM=");

				String dbPwd_tDBOutput_4 = decryptedPassword_tDBOutput_4;
				java.lang.Class.forName(driverClass_tDBOutput_4);

				conn_tDBOutput_4 = java.sql.DriverManager.getConnection(url_tDBOutput_4, dbUser_tDBOutput_4,
						dbPwd_tDBOutput_4);

				resourceMap.put("conn_tDBOutput_4", conn_tDBOutput_4);
				conn_tDBOutput_4.setAutoCommit(false);
				int commitEvery_tDBOutput_4 = 10000;
				int commitCounter_tDBOutput_4 = 0;

				int count_tDBOutput_4 = 0;

				java.sql.DatabaseMetaData dbMetaData_tDBOutput_4 = conn_tDBOutput_4.getMetaData();
				java.sql.ResultSet rsTable_tDBOutput_4 = dbMetaData_tDBOutput_4.getTables("Cube_olap", null, null,
						new String[] { "TABLE" });
				boolean whetherExist_tDBOutput_4 = false;
				while (rsTable_tDBOutput_4.next()) {
					String table_tDBOutput_4 = rsTable_tDBOutput_4.getString("TABLE_NAME");
					if (table_tDBOutput_4.equalsIgnoreCase("Dim_Categorie")) {
						whetherExist_tDBOutput_4 = true;
						break;
					}
				}
				if (whetherExist_tDBOutput_4) {
					try (java.sql.Statement stmtDrop_tDBOutput_4 = conn_tDBOutput_4.createStatement()) {
						stmtDrop_tDBOutput_4.execute("DROP TABLE `" + tableName_tDBOutput_4 + "`");
					}
				}
				try (java.sql.Statement stmtCreate_tDBOutput_4 = conn_tDBOutput_4.createStatement()) {
					stmtCreate_tDBOutput_4.execute("CREATE TABLE `" + tableName_tDBOutput_4
							+ "`(`ID_Categorie` INT(10)   not null ,`Nom_Categorie` VARCHAR(999)  ,`Description` VARCHAR(999)  ,primary key(`ID_Categorie`))");
				}

				String insert_tDBOutput_4 = "INSERT INTO `" + "Dim_Categorie"
						+ "` (`ID_Categorie`,`Nom_Categorie`,`Description`) VALUES (?,?,?)";
				int batchSize_tDBOutput_4 = 100;
				int batchSizeCounter_tDBOutput_4 = 0;

				java.sql.PreparedStatement pstmt_tDBOutput_4 = conn_tDBOutput_4.prepareStatement(insert_tDBOutput_4);
				resourceMap.put("pstmt_tDBOutput_4", pstmt_tDBOutput_4);

				/**
				 * [tDBOutput_4 begin ] stop
				 */

				/**
				 * [tMap_5 begin ] start
				 */

				ok_Hash.put("tMap_5", false);
				start_Hash.put("tMap_5", System.currentTimeMillis());

				currentComponent = "tMap_5";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row4");
				}

				int tos_count_tMap_5 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_5__Struct {
				}
				Var__tMap_5__Struct Var__tMap_5 = new Var__tMap_5__Struct();
// ###############################

// ###############################
// # Outputs initialization
				categorieStruct categorie_tmp = new categorieStruct();
// ###############################

				/**
				 * [tMap_5 begin ] stop
				 */

				/**
				 * [tDBInput_5 begin ] start
				 */

				ok_Hash.put("tDBInput_5", false);
				start_Hash.put("tDBInput_5", System.currentTimeMillis());

				currentComponent = "tDBInput_5";

				int tos_count_tDBInput_5 = 0;

				java.util.Calendar calendar_tDBInput_5 = java.util.Calendar.getInstance();
				calendar_tDBInput_5.set(0, 0, 0, 0, 0, 0);
				java.util.Date year0_tDBInput_5 = calendar_tDBInput_5.getTime();
				int nb_line_tDBInput_5 = 0;
				java.sql.Connection conn_tDBInput_5 = null;
				String driverClass_tDBInput_5 = "com.mysql.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_5 = java.lang.Class.forName(driverClass_tDBInput_5);
				String dbUser_tDBInput_5 = "root";

				final String decryptedPassword_tDBInput_5 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:4bethmRiHmiBqUBTZi0EOGM1I3iJfYCN9ixITZWlk70O9vFfB/L5GUnnySM=");

				String dbPwd_tDBInput_5 = decryptedPassword_tDBInput_5;

				String properties_tDBInput_5 = "noDatetimeStringSync=true";
				if (properties_tDBInput_5 == null || properties_tDBInput_5.trim().length() == 0) {
					properties_tDBInput_5 = "";
				}
				String url_tDBInput_5 = "jdbc:mysql://" + "127.0.0.1" + ":" + "3306" + "/" + "data_warehouse_vente"
						+ "?" + properties_tDBInput_5;

				conn_tDBInput_5 = java.sql.DriverManager.getConnection(url_tDBInput_5, dbUser_tDBInput_5,
						dbPwd_tDBInput_5);

				java.sql.Statement stmt_tDBInput_5 = conn_tDBInput_5.createStatement();

				String dbquery_tDBInput_5 = "SELECT \n  `categorie`.`ID_Categorie`, \n  `categorie`.`Nom_Categorie`, \n  `categorie`.`Description`\nFROM `categorie`";

				globalMap.put("tDBInput_5_QUERY", dbquery_tDBInput_5);
				java.sql.ResultSet rs_tDBInput_5 = null;

				try {
					rs_tDBInput_5 = stmt_tDBInput_5.executeQuery(dbquery_tDBInput_5);
					java.sql.ResultSetMetaData rsmd_tDBInput_5 = rs_tDBInput_5.getMetaData();
					int colQtyInRs_tDBInput_5 = rsmd_tDBInput_5.getColumnCount();

					String tmpContent_tDBInput_5 = null;

					while (rs_tDBInput_5.next()) {
						nb_line_tDBInput_5++;

						if (colQtyInRs_tDBInput_5 < 1) {
							row4.ID_Categorie = 0;
						} else {

							row4.ID_Categorie = rs_tDBInput_5.getInt(1);
							if (rs_tDBInput_5.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_5 < 2) {
							row4.Nom_Categorie = null;
						} else {

							row4.Nom_Categorie = routines.system.JDBCUtil.getString(rs_tDBInput_5, 2, false);
						}
						if (colQtyInRs_tDBInput_5 < 3) {
							row4.Description = null;
						} else {

							row4.Description = routines.system.JDBCUtil.getString(rs_tDBInput_5, 3, false);
						}

						/**
						 * [tDBInput_5 begin ] stop
						 */

						/**
						 * [tDBInput_5 main ] start
						 */

						currentComponent = "tDBInput_5";

						tos_count_tDBInput_5++;

						/**
						 * [tDBInput_5 main ] stop
						 */

						/**
						 * [tDBInput_5 process_data_begin ] start
						 */

						currentComponent = "tDBInput_5";

						/**
						 * [tDBInput_5 process_data_begin ] stop
						 */

						/**
						 * [tMap_5 main ] start
						 */

						currentComponent = "tMap_5";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row4"

							);
						}

						boolean hasCasePrimitiveKeyWithNull_tMap_5 = false;

						// ###############################
						// # Input tables (lookups)
						boolean rejectedInnerJoin_tMap_5 = false;
						boolean mainRowRejected_tMap_5 = false;

						// ###############################
						{ // start of Var scope

							// ###############################
							// # Vars tables

							Var__tMap_5__Struct Var = Var__tMap_5;// ###############################
							// ###############################
							// # Output tables

							categorie = null;

// # Output table : 'categorie'
							categorie_tmp.ID_Categorie = row4.ID_Categorie;
							categorie_tmp.Nom_Categorie = row4.Nom_Categorie;
							categorie_tmp.Description = row4.Description;
							categorie = categorie_tmp;
// ###############################

						} // end of Var scope

						rejectedInnerJoin_tMap_5 = false;

						tos_count_tMap_5++;

						/**
						 * [tMap_5 main ] stop
						 */

						/**
						 * [tMap_5 process_data_begin ] start
						 */

						currentComponent = "tMap_5";

						/**
						 * [tMap_5 process_data_begin ] stop
						 */
// Start of branch "categorie"
						if (categorie != null) {

							/**
							 * [tDBOutput_4 main ] start
							 */

							currentComponent = "tDBOutput_4";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "categorie"

								);
							}

							whetherReject_tDBOutput_4 = false;
							pstmt_tDBOutput_4.setInt(1, categorie.ID_Categorie);

							if (categorie.Nom_Categorie == null) {
								pstmt_tDBOutput_4.setNull(2, java.sql.Types.VARCHAR);
							} else {
								pstmt_tDBOutput_4.setString(2, categorie.Nom_Categorie);
							}

							if (categorie.Description == null) {
								pstmt_tDBOutput_4.setNull(3, java.sql.Types.VARCHAR);
							} else {
								pstmt_tDBOutput_4.setString(3, categorie.Description);
							}

							pstmt_tDBOutput_4.addBatch();
							nb_line_tDBOutput_4++;

							batchSizeCounter_tDBOutput_4++;
							if (batchSize_tDBOutput_4 <= batchSizeCounter_tDBOutput_4) {
								try {
									int countSum_tDBOutput_4 = 0;
									for (int countEach_tDBOutput_4 : pstmt_tDBOutput_4.executeBatch()) {
										countSum_tDBOutput_4 += (countEach_tDBOutput_4 == java.sql.Statement.EXECUTE_FAILED
												? 0
												: 1);
									}
									rowsToCommitCount_tDBOutput_4 += countSum_tDBOutput_4;
									insertedCount_tDBOutput_4 += countSum_tDBOutput_4;
								} catch (java.sql.BatchUpdateException e) {
									globalMap.put("tDBOutput_4_ERROR_MESSAGE", e.getMessage());
									int countSum_tDBOutput_4 = 0;
									for (int countEach_tDBOutput_4 : e.getUpdateCounts()) {
										countSum_tDBOutput_4 += (countEach_tDBOutput_4 < 0 ? 0 : countEach_tDBOutput_4);
									}
									rowsToCommitCount_tDBOutput_4 += countSum_tDBOutput_4;
									insertedCount_tDBOutput_4 += countSum_tDBOutput_4;
									System.err.println(e.getMessage());
								}

								batchSizeCounter_tDBOutput_4 = 0;
							}
							commitCounter_tDBOutput_4++;

							if (commitEvery_tDBOutput_4 <= commitCounter_tDBOutput_4) {

								try {
									int countSum_tDBOutput_4 = 0;
									for (int countEach_tDBOutput_4 : pstmt_tDBOutput_4.executeBatch()) {
										countSum_tDBOutput_4 += (countEach_tDBOutput_4 < 0 ? 0 : 1);
									}
									rowsToCommitCount_tDBOutput_4 += countSum_tDBOutput_4;
									insertedCount_tDBOutput_4 += countSum_tDBOutput_4;
								} catch (java.sql.BatchUpdateException e) {
									globalMap.put("tDBOutput_4_ERROR_MESSAGE", e.getMessage());
									int countSum_tDBOutput_4 = 0;
									for (int countEach_tDBOutput_4 : e.getUpdateCounts()) {
										countSum_tDBOutput_4 += (countEach_tDBOutput_4 < 0 ? 0 : countEach_tDBOutput_4);
									}
									rowsToCommitCount_tDBOutput_4 += countSum_tDBOutput_4;
									insertedCount_tDBOutput_4 += countSum_tDBOutput_4;
									System.err.println(e.getMessage());

								}
								if (rowsToCommitCount_tDBOutput_4 != 0) {
								}
								conn_tDBOutput_4.commit();
								if (rowsToCommitCount_tDBOutput_4 != 0) {
									rowsToCommitCount_tDBOutput_4 = 0;
								}
								commitCounter_tDBOutput_4 = 0;

							}

							tos_count_tDBOutput_4++;

							/**
							 * [tDBOutput_4 main ] stop
							 */

							/**
							 * [tDBOutput_4 process_data_begin ] start
							 */

							currentComponent = "tDBOutput_4";

							/**
							 * [tDBOutput_4 process_data_begin ] stop
							 */

							/**
							 * [tDBOutput_4 process_data_end ] start
							 */

							currentComponent = "tDBOutput_4";

							/**
							 * [tDBOutput_4 process_data_end ] stop
							 */

						} // End of branch "categorie"

						/**
						 * [tMap_5 process_data_end ] start
						 */

						currentComponent = "tMap_5";

						/**
						 * [tMap_5 process_data_end ] stop
						 */

						/**
						 * [tDBInput_5 process_data_end ] start
						 */

						currentComponent = "tDBInput_5";

						/**
						 * [tDBInput_5 process_data_end ] stop
						 */

						/**
						 * [tDBInput_5 end ] start
						 */

						currentComponent = "tDBInput_5";

					}
				} finally {
					if (rs_tDBInput_5 != null) {
						rs_tDBInput_5.close();
					}
					if (stmt_tDBInput_5 != null) {
						stmt_tDBInput_5.close();
					}
					if (conn_tDBInput_5 != null && !conn_tDBInput_5.isClosed()) {

						conn_tDBInput_5.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}

				}

				globalMap.put("tDBInput_5_NB_LINE", nb_line_tDBInput_5);

				ok_Hash.put("tDBInput_5", true);
				end_Hash.put("tDBInput_5", System.currentTimeMillis());

				/**
				 * [tDBInput_5 end ] stop
				 */

				/**
				 * [tMap_5 end ] start
				 */

				currentComponent = "tMap_5";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row4");
				}

				ok_Hash.put("tMap_5", true);
				end_Hash.put("tMap_5", System.currentTimeMillis());

				/**
				 * [tMap_5 end ] stop
				 */

				/**
				 * [tDBOutput_4 end ] start
				 */

				currentComponent = "tDBOutput_4";

				try {
					if (batchSizeCounter_tDBOutput_4 != 0) {
						int countSum_tDBOutput_4 = 0;

						for (int countEach_tDBOutput_4 : pstmt_tDBOutput_4.executeBatch()) {
							countSum_tDBOutput_4 += (countEach_tDBOutput_4 == java.sql.Statement.EXECUTE_FAILED ? 0
									: 1);
						}
						rowsToCommitCount_tDBOutput_4 += countSum_tDBOutput_4;

						insertedCount_tDBOutput_4 += countSum_tDBOutput_4;

					}

				} catch (java.sql.BatchUpdateException e) {
					globalMap.put(currentComponent + "_ERROR_MESSAGE", e.getMessage());

					int countSum_tDBOutput_4 = 0;
					for (int countEach_tDBOutput_4 : e.getUpdateCounts()) {
						countSum_tDBOutput_4 += (countEach_tDBOutput_4 < 0 ? 0 : countEach_tDBOutput_4);
					}
					rowsToCommitCount_tDBOutput_4 += countSum_tDBOutput_4;

					insertedCount_tDBOutput_4 += countSum_tDBOutput_4;

					System.err.println(e.getMessage());

				}
				batchSizeCounter_tDBOutput_4 = 0;

				if (pstmt_tDBOutput_4 != null) {

					pstmt_tDBOutput_4.close();
					resourceMap.remove("pstmt_tDBOutput_4");

				}
				resourceMap.put("statementClosed_tDBOutput_4", true);
				if (commitCounter_tDBOutput_4 > 0 && rowsToCommitCount_tDBOutput_4 != 0) {

				}
				conn_tDBOutput_4.commit();
				if (commitCounter_tDBOutput_4 > 0 && rowsToCommitCount_tDBOutput_4 != 0) {

					rowsToCommitCount_tDBOutput_4 = 0;
				}
				commitCounter_tDBOutput_4 = 0;

				conn_tDBOutput_4.close();

				resourceMap.put("finish_tDBOutput_4", true);

				nb_line_deleted_tDBOutput_4 = nb_line_deleted_tDBOutput_4 + deletedCount_tDBOutput_4;
				nb_line_update_tDBOutput_4 = nb_line_update_tDBOutput_4 + updatedCount_tDBOutput_4;
				nb_line_inserted_tDBOutput_4 = nb_line_inserted_tDBOutput_4 + insertedCount_tDBOutput_4;
				nb_line_rejected_tDBOutput_4 = nb_line_rejected_tDBOutput_4 + rejectedCount_tDBOutput_4;

				globalMap.put("tDBOutput_4_NB_LINE", nb_line_tDBOutput_4);
				globalMap.put("tDBOutput_4_NB_LINE_UPDATED", nb_line_update_tDBOutput_4);
				globalMap.put("tDBOutput_4_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_4);
				globalMap.put("tDBOutput_4_NB_LINE_DELETED", nb_line_deleted_tDBOutput_4);
				globalMap.put("tDBOutput_4_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_4);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "categorie");
				}

				ok_Hash.put("tDBOutput_4", true);
				end_Hash.put("tDBOutput_4", System.currentTimeMillis());

				/**
				 * [tDBOutput_4 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBInput_5 finally ] start
				 */

				currentComponent = "tDBInput_5";

				/**
				 * [tDBInput_5 finally ] stop
				 */

				/**
				 * [tMap_5 finally ] start
				 */

				currentComponent = "tMap_5";

				/**
				 * [tMap_5 finally ] stop
				 */

				/**
				 * [tDBOutput_4 finally ] start
				 */

				currentComponent = "tDBOutput_4";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_4") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_4 = null;
						if ((pstmtToClose_tDBOutput_4 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_4")) != null) {
							pstmtToClose_tDBOutput_4.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_4") == null) {
						java.sql.Connection ctn_tDBOutput_4 = null;
						if ((ctn_tDBOutput_4 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_4")) != null) {
							try {
								ctn_tDBOutput_4.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_4) {
								String errorMessage_tDBOutput_4 = "failed to close the connection in tDBOutput_4 :"
										+ sqlEx_tDBOutput_4.getMessage();
								System.err.println(errorMessage_tDBOutput_4);
							}
						}
					}
				}

				/**
				 * [tDBOutput_4 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_5_SUBPROCESS_STATE", 1);
	}

	public void tMap_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tMap_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				/**
				 * [tMap_1 begin ] start
				 */

				ok_Hash.put("tMap_1", false);
				start_Hash.put("tMap_1", System.currentTimeMillis());

				currentComponent = "tMap_1";

				int tos_count_tMap_1 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
// ###############################

// ###############################
// # Outputs initialization
// ###############################

				/**
				 * [tMap_1 begin ] stop
				 */

				/**
				 * [tMap_1 main ] start
				 */

				currentComponent = "tMap_1";

				boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;

				// ###############################
				// # Input tables (lookups)
				boolean rejectedInnerJoin_tMap_1 = false;
				boolean mainRowRejected_tMap_1 = false;

				// ###############################
				{ // start of Var scope

					// ###############################
					// # Vars tables
					// ###############################
					// ###############################
					// # Output tables

// ###############################

				} // end of Var scope

				rejectedInnerJoin_tMap_1 = false;

				tos_count_tMap_1++;

				/**
				 * [tMap_1 main ] stop
				 */

				/**
				 * [tMap_1 process_data_begin ] start
				 */

				currentComponent = "tMap_1";

				/**
				 * [tMap_1 process_data_begin ] stop
				 */

				/**
				 * [tMap_1 process_data_end ] start
				 */

				currentComponent = "tMap_1";

				/**
				 * [tMap_1 process_data_end ] stop
				 */

				/**
				 * [tMap_1 end ] start
				 */

				currentComponent = "tMap_1";

// ###############################
// # Lookup hashes releasing
// ###############################      

				ok_Hash.put("tMap_1", true);
				end_Hash.put("tMap_1", System.currentTimeMillis());

				/**
				 * [tMap_1 end ] stop
				 */
			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tMap_1 finally ] start
				 */

				currentComponent = "tMap_1";

				/**
				 * [tMap_1 finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tMap_1_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	protected PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	public static void main(String[] args) {
		final Dim_temp Dim_tempClass = new Dim_temp();

		int exitCode = Dim_tempClass.runJobInTOS(args);

		System.exit(exitCode);
	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}
		enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		if (rootPid == null) {
			rootPid = pid;
		}
		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}
		boolean inOSGi = routines.system.BundleUtils.inOSGi();

		if (inOSGi) {
			java.util.Dictionary<String, Object> jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

			if (jobProperties != null && jobProperties.get("context") != null) {
				contextStr = (String) jobProperties.get("context");
			}
		}

		try {
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = Dim_temp.class.getClassLoader()
					.getResourceAsStream("local_project/dim_temp_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = Dim_temp.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				try {
					// defaultProps is in order to keep the original context value
					if (context != null && context.isEmpty()) {
						defaultProps.load(inContext);
						context = new ContextProperties(defaultProps);
					}
				} finally {
					inContext.close();
				}
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, parametersToEncrypt));

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tDBInput_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tDBInput_1) {
			globalMap.put("tDBInput_1_SUBPROCESS_STATE", -1);

			e_tDBInput_1.printStackTrace();

		}
		try {
			errorCode = null;
			tDBInput_2Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tDBInput_2) {
			globalMap.put("tDBInput_2_SUBPROCESS_STATE", -1);

			e_tDBInput_2.printStackTrace();

		}
		try {
			errorCode = null;
			tDBInput_3Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tDBInput_3) {
			globalMap.put("tDBInput_3_SUBPROCESS_STATE", -1);

			e_tDBInput_3.printStackTrace();

		}
		try {
			errorCode = null;
			tDBInput_4Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tDBInput_4) {
			globalMap.put("tDBInput_4_SUBPROCESS_STATE", -1);

			e_tDBInput_4.printStackTrace();

		}
		try {
			errorCode = null;
			tDBInput_5Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tDBInput_5) {
			globalMap.put("tDBInput_5_SUBPROCESS_STATE", -1);

			e_tDBInput_5.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : Dim_temp");
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		int returnCode = 0;

		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {// for trunjob call
			final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 224367 characters generated by Talend Open Studio for Data Integration on the
 * 29 juin 2024, 01:47:02 CEST
 ************************************************************************************************/