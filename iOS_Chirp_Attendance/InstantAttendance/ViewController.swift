//
//  ViewController.swift
//  InstantAttendance
//
//  Created by Garima Bothra on 08/09/19.
//  Copyright © 2019 Garima Bothra. All rights reserved.
//

import UIKit
import Firebase
import SVProgressHUD

class ViewController: UIViewController {

    @IBOutlet weak var password: UITextField!
    @IBOutlet weak var email: UITextField!
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }

    @IBAction func login(_ sender: Any) {
        Auth.auth().signIn(withEmail: email.text!, password: password.text!) { (user, error) in
            
            if error != nil {
                print(error!)
            }
            else{
                print("Login Successful")
                SVProgressHUD.dismiss()
                
    }
    
}

}
}
