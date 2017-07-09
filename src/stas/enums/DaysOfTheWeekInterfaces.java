package stas.enums;

interface DayOfWeek {
	boolean isWeekend();
}

// *************************************************************************
public enum DaysOfTheWeekInterfaces implements DayOfWeek {
	MONDAY() {
		@Override
		public boolean isWeekend() {
			return false;
		}
	},
	TUESDAY() {
		@Override
		public boolean isWeekend() {
			return false;
		}
	},
	WEDNESDAY() {
		@Override
		public boolean isWeekend() {
			return false;
		}
	},
	THURSDAY() {
		@Override
		public boolean isWeekend() {
			return false;
		}
	},
	FRIDAY() {
		@Override
		public boolean isWeekend() {
			return false;
		}

		public String mmm(String a) {
			return "ssdf";
		}
	},
	SATURDAY() {
		@Override
		public boolean isWeekend() {
			return true;
		}
	},
	SUNDAY() {
		@Override
		public boolean isWeekend() {
			return true;
		}
	}
}
