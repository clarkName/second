package test;


public class Test3
{
	public static void main(String[] args)
	{
		double a=0.06+0.01;
		System.out.println(a);
		for (HaiDaiStateMapping value : HaiDaiStateMapping.values())
		{
			
			System.out.println(value.getWldState());
		}
		
		getCarryParameter(2);
	}
	private static void getCarryParameter(int i)
	{

		switch (i)
		{
		case 1:System.out.println(1);
		case 2:System.out.println(2);
		case 3:System.out.println(3);
		}
	}
	private static enum HaiDaiStateMapping
	{
		PENDING_AIP("pending_aip", "1"), // 贷款订单通过审核
		INIT_AIP("aip", "2"), // 筹款中，等待到账
		AIP("disbursed", "3"), // 放款已到账
		PUSH_BACKED("closed", "4"), // 贷款还款已偿清
		REJECTED("rejected", "9"), // 未通过的贷款订单
		;
		private final String wldState;
		private final String haidaiState;

		private HaiDaiStateMapping(String wldState, String haidaiState)
		{
			this.wldState = wldState;
			this.haidaiState = haidaiState;
		}

		public String getWldState()
		{
			return wldState;
		}

		public String getHaidaiState()
		{
			return haidaiState;
		}

		public static String getHaidaiState(String wldState)
		{
			for (HaiDaiStateMapping value : values())
			{
				if (value.getWldState().equals(wldState))
				{
					return value.getHaidaiState();
				}
			}
			return null;
		}
	}
}
