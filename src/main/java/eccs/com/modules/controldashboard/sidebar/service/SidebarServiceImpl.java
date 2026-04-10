package eccs.com.modules.controldashboard.sidebar.service;

import eccs.com.modules.controldashboard.sidebar.query.SidebarQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SidebarServiceImpl implements SidebarService {

    private final SidebarQuery sidebarQuery;

    @Override
    public Object getMenu(Integer id) {
        return sidebarQuery.findMenuByIdEmpleado(id);
    }
}
