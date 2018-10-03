package part01;
/**
 * USING OLD SPEC
 * This is a enum for the supplier region
 * @author Michael Gilroy 40203084
 *
 */
public enum SupRegion {
	UNITED_KINGDOM {
		@Override
		String getRegionAsString() {
			// TODO Auto-generated method stub
			return "United Kingdom";
		}
	}, EUROPE {
		@Override
		String getRegionAsString() {
			// TODO Auto-generated method stub
			return "Europe";
		}
	}, OUTSIDE_EU {
		@Override
		String getRegionAsString() {
			// TODO Auto-generated method stub
			return "Outside European Union";
		}
	};
	
	abstract String getRegionAsString();
}
