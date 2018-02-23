#|
  This file is a part of sicp project.
  Copyright (c) 2018 Kent OHASHI (ignorantia.juris.non.excusa@gmail.com)
|#

#|
  Author: Kent OHASHI (ignorantia.juris.non.excusa@gmail.com)
|#

(defsystem "sicp"
  :version "0.1.0"
  :author "Kent OHASHI"
  :license "MIT"
  :depends-on ()
  :components ((:module "src"
                :components
                ((:file "sicp"))))
  :description ""
  :long-description
  #.(read-file-string
     (subpathname *load-pathname* "README.md"))
  :in-order-to ((test-op (test-op "sicp-test"))))
