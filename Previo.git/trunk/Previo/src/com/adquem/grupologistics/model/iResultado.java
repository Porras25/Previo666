package com.adquem.grupologistics.model;

import com.google.gson.annotations.SerializedName;

public class iResultado {
	    @SerializedName("idItemApp")
		private long idItemApp;
	    @SerializedName("idItem")
		private Long idItem;
		public long getIdItemApp() {
			return idItemApp;
		}
		public void setIdItemApp(long idItemApp) {
			this.idItemApp = idItemApp;
		}
		public Long getIdItem() {
			return idItem;
		}
		public void setIdItem(Long idItem) {
			this.idItem = idItem;
		}
}
