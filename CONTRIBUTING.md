Thanks for wanting to contribute to this project. Here are the steps for contributing-

1) Raise an issue stating the bug fixes/enhancements you want to make.
2) Open a pull request for the issue.
3) Properly fill the [pull request template](https://github.com/Aatmaj-Zephyr/ANN4j/blob/main/.github/PULL_REQUEST_TEMPLATE.md). Make sure the code is properly formatted and proper documentation has been added.
You will need to enter @param and @return feilds for every method.
4) Once you make your change, you will require to test all old features mentioned in the main class by running either [this code](https://github.com/Aatmaj-Zephyr/ANN4j/blob/072d53ef795af080e15efcea620b03c6dab1a06a/Main.java) or running any other code which tests the present functionality with the following parameters.
    -  MNIST OCR database
    -  45000 train samples for 10 epochs
    -  9900 test samples
    -  Learning rate 1
    -  Batchsize 10



5) You will also be required to test the functionality you added if any by running test drive code similar to [this sample](https://github.com/Aatmaj-Zephyr/ANN4j/blob/072d53ef795af080e15efcea620b03c6dab1a06a/Main.java). The test drive must have the above parameters.
6) The output files of both the testing of old functionality and new functionlity need to be submitted in the pull request.
7) Your pull request will be reviewed and will be merged if it meets the requirements in due course of time.


Your pull request may not be accepted beacuse of the following reasons
1) The pull request template is not filled.
2) Relevant documentation to the code is not provided.
3) Old funtionality after the change is not tested.
4) New functionality (if any) is not tested.
5) The code is unreadable and does not meet clean code standerds like proper variable nameing and comments.
6) The functionality is not required at all and is counterproductive. (This will be stated in step 1 after raising the issue.)



