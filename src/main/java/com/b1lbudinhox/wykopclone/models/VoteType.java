package com.b1lbudinhox.wykopclone.models;

import com.b1lbudinhox.wykopclone.exceptions.SpringAppException;

public enum VoteType {
    UPVOTE(1), DOWNVOTE(-1);
    private final int direction;
    VoteType(int direction) {
        this.direction = direction;
    }
    public static VoteType lookup(Integer direction) {
        for (VoteType value : VoteType.values()) {
            if(value.direction == direction){
                return value;
            }
        }
        throw new SpringAppException("Vote not found");
    }
}
