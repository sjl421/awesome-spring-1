package com.ternence.spring.skills.design.command.command;

import com.ternence.spring.skills.design.command.receiver.TeamMember;

/**
 * @author TAOJIANGHANG
 * @since 2018/11/16 15:24
 */
public class ConcreteDevelopCommand implements Command {

    /**
     * 这个命令的作用就是让某个开发者做事情
     */
    private TeamMember someDeveloper;

    public ConcreteDevelopCommand(TeamMember someDeveloper) {
        this.someDeveloper = someDeveloper;
    }

    /**
     * 向开发发出的命令
     */
    @Override
    public void action(Object... someParameters) {
        someDeveloper.doAction(someParameters);
    }
}
