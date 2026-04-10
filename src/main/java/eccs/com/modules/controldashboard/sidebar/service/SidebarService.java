package eccs.com.modules.controldashboard.sidebar.service;

import eccs.com.core.dtos.ResponseDto;

public interface SidebarService {
    ResponseDto<Object> getMenu(Integer id);
}
