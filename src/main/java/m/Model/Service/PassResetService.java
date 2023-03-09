package m.Model.Service;


import m.Model.Entity.PasswordResetToken;

public interface PassResetService {
    PasswordResetToken saveOrUpdate(PasswordResetToken passwordResetToken);
    PasswordResetToken getLastTokenByUserId(int userId);
}
