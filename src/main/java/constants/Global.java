package constants;

import lombok.experimental.UtilityClass;
import model.branch.BranchList;

import java.util.HashMap;

@UtilityClass
public class Global {

    public static BranchList BRANCH_LIST = BranchList.builder()
                                                     .branchInfoMap(new HashMap<>())
                                                     .build();
}
