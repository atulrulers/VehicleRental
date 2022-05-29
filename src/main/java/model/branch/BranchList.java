package model.branch;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class BranchList {
    private Map<String, BranchInfo> branchInfoMap;
}
