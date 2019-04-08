package com.comparator;

import java.util.Comparator;

import com.bean.Trainer;

public class TDateOfBeginComparator implements Comparator<Trainer>{


	@Override
	public int compare(Trainer t1, Trainer t2) {
		// TODO Auto-generated method stub
		
		if(t1.gettDateOfBegin().getTime()>t2.gettDateOfBegin().getTime())
			return 1;
		else if(t1.gettDateOfBegin().getTime()<t2.gettDateOfBegin().getTime())
			return -1;
		return 0;
	}

}
