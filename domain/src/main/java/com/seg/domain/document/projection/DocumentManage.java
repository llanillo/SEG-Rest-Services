package com.seg.domain.document.projection;

import com.seg.domain.commission.dto.CommissionSummary;
import com.seg.domain.enumeration.Action;

public interface DocumentManage extends DocumentDetails{
        
    Action getAction();
    CommissionSummary getCommission();
}
