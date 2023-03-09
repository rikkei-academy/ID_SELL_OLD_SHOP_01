package m.Model.ServiceImp;

import m.Model.Entity.PasswordResetToken;
import m.Model.Responsitory.PassResetRepository;
import m.Model.Service.PassResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassResetServiceImpl implements PassResetService {
    @Autowired
    private PassResetRepository passResetRepository;
    @Override
    public PasswordResetToken saveOrUpdate(PasswordResetToken passwordResetToken) {
        return passResetRepository.save(passwordResetToken);
    }
    @Override
    public PasswordResetToken getLastTokenByUserId(int userId) {
        return passResetRepository.getLastTokenByUserId(userId);
    }
}
