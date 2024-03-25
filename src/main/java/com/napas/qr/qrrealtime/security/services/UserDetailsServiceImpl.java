package com.napas.qr.qrrealtime.security.services;

import com.napas.qr.qrrealtime.entity.*;
import com.napas.qr.qrrealtime.models.UserModel;
import com.napas.qr.qrrealtime.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MasterMerchantRepository masterMerchantDAO;

    @Autowired
    private MerchantCorporateRepository merchantCorporateDAO;

    @Autowired
    private MerchantBranchRepository branchDAO;

    @Autowired
    private MerchantCashierRepository cashierDAO;

    @Autowired
    private MerchantPersonalRepository merchantPersonalDAO;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TblOrgUser user = userRepository.findByUsername(username)
               .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        TblMasterMerchant masterMerchant = null;
        TblMerchantCorporate merchantCorporate = null;
        TblMerchantBranch merchantBranch = null;
        TblMerchantCashier cashier = null;
        TblMerchantPersonal personal = null;

        switch(user.getTargetType()) {
            case MASTER:
                masterMerchant = masterMerchantDAO.findById(user.getTargetId())
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
                break;
            case MERCHANT:
                merchantCorporate = merchantCorporateDAO.findById(user.getTargetId())
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

                masterMerchant = merchantCorporate.getTblMasterMerchant();
                break;
            case BRANCH:
                merchantBranch = branchDAO.findById(user.getTargetId())
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
                merchantCorporate = merchantBranch.getTblMerchantCorporate();
                masterMerchant = merchantCorporate.getTblMasterMerchant();
                break;
            case CASHIER:
                cashier = cashierDAO.findById(user.getTargetId())
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

                merchantBranch = cashier.getTblMerchantBranch();
                merchantCorporate = merchantBranch.getTblMerchantCorporate();
                masterMerchant = merchantCorporate.getTblMasterMerchant();
                break;
            case PERSONAL:

                personal = merchantPersonalDAO.findById(user.getTargetId())
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
                masterMerchant = personal.getTblMasterMerchant();
                break;
        }

        UserModel userModel = new UserModel(user.getPassword(), user.getId(), username, user.getTargetType(),
                user.getTargetId(), masterMerchant, merchantCorporate, merchantBranch, cashier, personal, user.getRole());

        return UserDetailsImpl.build(userModel);
    }

}
